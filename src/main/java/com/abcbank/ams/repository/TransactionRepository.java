package com.abcbank.ams.repository;

import com.abcbank.ams.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionsBySenderAccountNumberAndTransactionDateBetween(Long senderAccountNumber, Date from, Date to);
    List<Transaction> findTransactionsByReceiverAccountNumberAndTransactionDateBetween(Long receiverAccountNumber, Date from, Date to);

}
