package com.abcbank.ams.mapper;

import com.abcbank.ams.entity.Address;
import com.abcbank.ams.model.UserAddress;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AddressMapper {

    public static final AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    public abstract Address map(UserAddress userAddress);
    public abstract UserAddress map(Address address);

}
