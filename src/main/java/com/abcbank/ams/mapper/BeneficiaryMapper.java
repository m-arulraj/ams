package com.abcbank.ams.mapper;

import com.abcbank.ams.entity.Account;
import com.abcbank.ams.model.BeneficiaryDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class BeneficiaryMapper {

    public static final BeneficiaryMapper INSTANCE = Mappers.getMapper(BeneficiaryMapper.class);

    @Mapping(source = "accountHolderName", target = "fullName")
    public abstract BeneficiaryDetails map(Account account);

    public abstract List<BeneficiaryDetails> map(List<Account> account);

}
