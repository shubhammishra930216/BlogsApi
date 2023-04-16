package com.shubhammishra.blogsapi.services;

import com.shubhammishra.blogsapi.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Long userId);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();

    void deleteUsers(Long userId);



}
