package com.abcbank.ams.service.impl;

import com.abcbank.ams.entity.UserCredentials;
import com.abcbank.ams.repository.UserCredentialsRepository;
import com.abcbank.ams.service.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsServiceImpl implements UserCredentialsService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Override
    public UserCredentials getUserCredentials(String emailId) {
        return userCredentialsRepository.getById(emailId);
    }

}
