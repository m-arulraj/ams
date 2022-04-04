package com.abcbank.ams.controller;

import com.abcbank.ams.common.constants.AmsConstants;
import com.abcbank.ams.common.exception.AmsException;
import com.abcbank.ams.common.properties.OtpProperties;
import com.abcbank.ams.common.util.JwtTokenUtility;
import com.abcbank.ams.model.AuthenticationRequest;
import com.abcbank.ams.model.OtpRequest;
import com.abcbank.ams.model.TokenResponse;
import com.abcbank.ams.service.UserService;
import com.abcbank.ams.service.impl.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtility jwtTokenUtility;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private OtpProperties otpProperties;

    @PostMapping(value = "/login")
    public ResponseEntity<Long> createAuthSession(HttpServletRequest request, @RequestBody AuthenticationRequest authRequest) throws AmsException {
        authenticate(authRequest.getEmailId(), authRequest.getPassword());
        long authInstanceId = System.currentTimeMillis();
        request.getSession().setAttribute(AmsConstants.AUTH_INSTANCE_ID, authInstanceId);
        request.getSession().setAttribute(AmsConstants.AUTH_USER_EMAIL_ID, authRequest.getEmailId());
        return ResponseEntity.status(HttpStatus.OK).body(authInstanceId);
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<TokenResponse> getAuthToken(HttpServletRequest request, @RequestBody OtpRequest otpRequest) throws AmsException {
        validateOtpRequest(request,otpRequest);
        String userEmailId = request.getSession().getAttribute(AmsConstants.AUTH_USER_EMAIL_ID).toString();
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userEmailId);
        String token = jwtTokenUtility.generateToken(userDetails);
        Long userId = userService.getUserId(userEmailId);
        request.getSession().invalidate();
        return ResponseEntity.status(HttpStatus.OK).body(new TokenResponse(token, AmsConstants.TOKEN_TYPE, userId));
    }

    private void authenticate(String username, String password) throws AmsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new AmsException("ERR401", "Invalid Credentials");
        }
    }

    private void validateOtpRequest(HttpServletRequest request, OtpRequest otpRequest) throws AmsException {
        if (Long.parseLong(request.getSession().getAttribute(AmsConstants.AUTH_INSTANCE_ID).toString()) != otpRequest.getAuthInstanceId()) {
            throw new AmsException("ERR400", "Invalid Request");
        }
        if (!otpProperties.getCode().equals(otpRequest.getOtpCode())) {
            throw new AmsException("ERR400", "Invalid OTP Code");
        }
    }

}
