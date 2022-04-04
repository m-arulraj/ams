package com.abcbank.ams.model;

import lombok.Data;

@Data
public class OtpRequest {

    private Integer otpCode;
    private Long authInstanceId;

}
