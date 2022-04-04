package com.abcbank.ams.mapper;

import com.abcbank.ams.entity.Transaction;
import com.abcbank.ams.model.TransactionDetails;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TransactionMapper {

    public static final TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    public abstract Transaction map(TransactionDetails transactionDetails);
    public abstract TransactionDetails map(Transaction transaction);
    public abstract List<TransactionDetails> map(List<Transaction> transaction);

}
