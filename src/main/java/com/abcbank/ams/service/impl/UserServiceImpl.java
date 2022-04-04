package com.abcbank.ams.service.impl;

import com.abcbank.ams.common.constants.AmsConstants;
import com.abcbank.ams.common.exception.AmsException;
import com.abcbank.ams.entity.Account;
import com.abcbank.ams.entity.User;
import com.abcbank.ams.entity.UserCredentials;
import com.abcbank.ams.mapper.AddressMapper;
import com.abcbank.ams.mapper.UserMapper;
import com.abcbank.ams.model.UserDetails;
import com.abcbank.ams.repository.UserCredentialsRepository;
import com.abcbank.ams.repository.UserRepository;
import com.abcbank.ams.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void createUserAccount(UserDetails user) throws AmsException {
        boolean accountAlreadyExists = userCredentialsRepository.existsById(user.getEmailId());
        if (accountAlreadyExists) {
            throw new AmsException("ERR400","User profile already exists");
        }
        User userEntity = UserMapper.INSTANCE.map(user);
        userEntity.setCurrentAddress(AddressMapper.INSTANCE.map(user.getCurrentAddress()));
        userEntity.setPermanentAddress(AddressMapper.INSTANCE.map(user.getPermanentAddress()));

        Account account = new Account();
        account.setAccountHolderName(user.getFirstName() + " " + user.getLastName());
        account.setAccountBalance(AmsConstants.ACCOUNT_OPENING_BALANCE);
        userEntity.setAccount(account);

        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmailId(user.getEmailId());
        userCredentials.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserCredentials(userCredentials);

        userEntity = userRepository.save(userEntity);
        log.info("Profile created for email Id - {} with Account Number #{}",
                user.getEmailId(), userEntity.getAccount().getAccountNumber());
    }

    @Override
    public void updateUserDetails(UserDetails user) {
        User userEntity = userRepository.getById(user.getUserId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setDob(user.getDob());
        userEntity.setGender(user.getGender());
        userEntity.setPanNumber(user.getPanNumber());
        userEntity.setCurrentAddress(AddressMapper.INSTANCE.map(user.getCurrentAddress()));
        userEntity.setPermanentAddress(AddressMapper.INSTANCE.map(user.getPermanentAddress()));
        userRepository.save(userEntity);
        log.info("User Details Updated for User Id - {}", user.getUserId());
    }

    @Override
    public void updateUserPassword(com.abcbank.ams.model.UserCredentials userCredentials) {
        UserCredentials userCredentialsEntity = userCredentialsRepository.getById(userCredentials.getEmailId());
        userCredentialsEntity.setPassword(bCryptPasswordEncoder.encode(userCredentials.getPassword()));
        userCredentialsRepository.save(userCredentialsEntity);
        log.info("User Password Updated for emailId - {}", userCredentials.getEmailId());
    }

    @Override
    public UserDetails getUserDetails(Long userId) {
        User user = userRepository.getById(userId);
        UserDetails userDetails = UserMapper.INSTANCE.map(user);
        userDetails.setPermanentAddress(AddressMapper.INSTANCE.map(user.getPermanentAddress()));
        userDetails.setCurrentAddress(AddressMapper.INSTANCE.map(user.getCurrentAddress()));
        return userDetails;
    }

    @Override
    public Long getUserId(String emailId) {
        return userRepository.findUserByUserCredentialsEmailId(emailId).getUserId();
    }

}
