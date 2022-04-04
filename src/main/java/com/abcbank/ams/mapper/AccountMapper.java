package com.abcbank.ams.mapper;

import com.abcbank.ams.entity.Account;
import com.abcbank.ams.model.AccountDetails;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AccountMapper {

    public static final AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    public abstract Account map(AccountDetails accountDetails);
    public abstract AccountDetails map(Account account);
    public abstract List<AccountDetails> map(List<Account> account);

}
