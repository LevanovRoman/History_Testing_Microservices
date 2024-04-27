package com.myapp.fronthistorytesting.admin_user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private String email;

    private String password;

    private String role;

    private String fullname;


}
