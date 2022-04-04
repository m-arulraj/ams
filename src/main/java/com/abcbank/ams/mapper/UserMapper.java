package com.abcbank.ams.mapper;

import com.abcbank.ams.entity.User;
import com.abcbank.ams.model.UserDetails;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User map(UserDetails user);

    @Mapping(source = "userCredentials.emailId", target = "emailId")
    public abstract UserDetails map(User user);

    @AfterMapping
    public void mapAddresses(User user, UserDetails userDetails) {
        userDetails.setCurrentAddress(AddressMapper.INSTANCE.map(user.getCurrentAddress()));
        userDetails.setPermanentAddress(AddressMapper.INSTANCE.map(user.getPermanentAddress()));
    }

}
