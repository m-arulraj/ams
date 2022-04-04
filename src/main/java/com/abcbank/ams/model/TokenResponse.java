package com.abcbank.ams.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse {

    private String token;
    private String tokenType;
    private Long userId;

}
