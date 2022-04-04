package com.abcbank.ams.controller;

import com.abcbank.ams.common.exception.AmsException;
import com.abcbank.ams.model.AccountDetails;
import com.abcbank.ams.service.AccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/account")
@SecurityRequirement(name = "authorize")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<AccountDetails> getAccountSummary(@PathVariable Long userId) throws AmsException {
        if (userId == null) {
            throw new AmsException("ERR400", "Invalid User ID");
        }
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountSummary(userId));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<AccountDetails>> getAllAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccounts());
    }

}
