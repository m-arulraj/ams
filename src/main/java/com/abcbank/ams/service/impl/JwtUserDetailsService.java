package com.abcbank.ams.service.impl;

import com.abcbank.ams.entity.UserCredentials;
import com.abcbank.ams.service.UserCredentialsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialsService userCredentialsService;

    private static final Logger log = LoggerFactory.getLogger(JwtUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String emailId) {
        try {
            UserCredentials userCredentials = userCredentialsService.getUserCredentials(emailId);
            return buildUserDetails(userCredentials);
        } catch (NullPointerException ex) {
            log.error("User not found : {} - {}", emailId, ex.getMessage());
            throw new UsernameNotFoundException("User Not Found");
        }
    }

    private UserDetails buildUserDetails(UserCredentials userCredentials) {
        return new User(userCredentials.getEmailId(), userCredentials.getPassword(), true, true, true, true, Collections.emptyList());
    }

}
