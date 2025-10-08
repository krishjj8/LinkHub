package com.linkhub.linkservice.dto;

import lombok.Data;

@Data
public class UserCreateRequestDto {
    private String username;
    private String email;
    private String password;
}
