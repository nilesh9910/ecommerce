package com.example.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorDTO {
    private String message;
    private int errorCode;
    private Date timeStamp;
    public ErrorDTO(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.timeStamp = new Date();
    }
}
