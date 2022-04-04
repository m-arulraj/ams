package com.abcbank.ams.controller;

import com.abcbank.ams.common.constants.AmsConstants;
import com.abcbank.ams.common.exception.AmsException;
import com.abcbank.ams.model.UserCredentials;
import com.abcbank.ams.model.UserDetails;
import com.abcbank.ams.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user/profile")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createUserProfile(@RequestBody UserDetails user) throws AmsException {
        userService.createUserAccount(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(AmsConstants.MESSAGE_USER_PROFILE_CREATED);
    }

    @GetMapping(value = "/{userId}")
    @SecurityRequirement(name = "authorize")
    public ResponseEntity<UserDetails> getUserDetails(@PathVariable Long userId) throws AmsException {
        if (userId == null) {
            throw new AmsException("ERR400", "Invalid User ID");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserDetails(userId));
    }

    @PostMapping(value = "/update")
    @SecurityRequirement(name = "authorize")
    public ResponseEntity<String> updateUserDetails(@RequestBody UserDetails user) {
        userService.updateUserDetails(user);
        return ResponseEntity.status(HttpStatus.OK).body(AmsConstants.MESSAGE_USER_PROFILE_UPDATED);
    }

    @PostMapping(value = "/password/update")
    @SecurityRequirement(name = "authorize")
    public ResponseEntity<String> updatePassword(@RequestBody UserCredentials userCredentials) {
        userService.updateUserPassword(userCredentials);
        return ResponseEntity.status(HttpStatus.OK).body(AmsConstants.MESSAGE_USER_PASSWORD_UPDATED);
    }

}
