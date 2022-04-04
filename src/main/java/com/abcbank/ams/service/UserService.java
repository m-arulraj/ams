package com.abcbank.ams.service;

import com.abcbank.ams.common.exception.AmsException;
import com.abcbank.ams.model.UserCredentials;
import com.abcbank.ams.model.UserDetails;

public interface UserService {

    void createUserAccount(UserDetails user) throws AmsException;
    void updateUserDetails(UserDetails user);
    void updateUserPassword(UserCredentials userCredentials);
    UserDetails getUserDetails(Long userId);
    Long getUserId(String emailId);

}
