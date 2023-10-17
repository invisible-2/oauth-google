package com.iongroup.api.oauth.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountDto {
    private Integer id;
    private String username;
    private UserDto user;
}
