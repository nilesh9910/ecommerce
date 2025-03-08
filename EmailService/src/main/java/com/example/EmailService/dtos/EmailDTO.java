package com.example.EmailService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {
    String to;
    String subject;
    String body;
}
