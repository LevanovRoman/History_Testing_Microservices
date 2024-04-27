package com.myapp.fronthistorytesting.admin_user.service;


import com.myapp.fronthistorytesting.admin_user.dto.UserDto;
import com.myapp.fronthistorytesting.admin_user.model.User;


public interface UserService {

    User save(UserDto userDto);
}
