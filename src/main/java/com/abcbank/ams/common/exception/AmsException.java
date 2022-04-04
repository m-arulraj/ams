package com.abcbank.ams.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AmsException extends Exception {

    private final String errorCode;
    private final String errorMessage;

}
