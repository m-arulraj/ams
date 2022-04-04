package com.abcbank.ams.repository;

import com.abcbank.ams.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionsBySenderAccountNumber(Long senderAccountNumber);
    List<Transaction> findTransactionsByReceiverAccountNumber(Long receiverAccountNumber);

}
