package com.shubhammishra.blogsapi.services.impl;

import com.shubhammishra.blogsapi.configurations.ModelMapperConfig;
import com.shubhammishra.blogsapi.dto.UserDto;
import com.shubhammishra.blogsapi.entiities.User;
import com.shubhammishra.blogsapi.exceptions.ResourceNotFoundException;
import com.shubhammishra.blogsapi.objectmappers.UserMapper;
import com.shubhammishra.blogsapi.repositories.UserRepository;
import com.shubhammishra.blogsapi.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    /**
     * @param user
     * @return
     */

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.userDtoToEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.userEntityToDto(savedUser);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public UserDto updateUser(UserDto userDto,Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        //userDto.setId(userId);
        User updatedUser = userMapper.userDtoToEntity(userDto);
        User savedUser = userRepository.save(updatedUser);
        return userMapper.userEntityToDto(savedUser);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        UserDto userDto = userMapper.userEntityToDto(user);

        return userDto;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> userMapper.userEntityToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    /**
     * @param userId
     */
    @Override
    public void deleteUsers(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.deleteById(userId);

    }


}
