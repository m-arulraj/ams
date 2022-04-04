package com.abcbank.ams.controller;

import com.abcbank.ams.common.constants.AmsConstants;
import com.abcbank.ams.model.TransactionDetails;
import com.abcbank.ams.service.TransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/transaction")
@SecurityRequirement(name = "authorize")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDetails transactionDetails) {
        transactionService.createTransaction(transactionDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(AmsConstants.MESSAGE_TRANSACTION_DONE);
    }

}
