package com.myapp.fronthistorytesting.admin_user.service;


import com.myapp.fronthistorytesting.admin_user.dto.UserDto;
import com.myapp.fronthistorytesting.admin_user.model.User;
import com.myapp.fronthistorytesting.admin_user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), "USER", userDto.getFullname());
        return userRepository.save(user);
    }
}
