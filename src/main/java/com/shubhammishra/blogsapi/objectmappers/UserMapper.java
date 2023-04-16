package com.shubhammishra.blogsapi.objectmappers;

import com.shubhammishra.blogsapi.dto.UserDto;
import com.shubhammishra.blogsapi.entiities.User;
import org.apache.tomcat.util.security.MD5Encoder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;
    public User userDtoToEntity(UserDto userDto){
        User user = modelMapper.map(userDto,User.class);


        /*
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setRoleId(userDto.getRoleId());
        user.setEmailId(userDto.getEmailId());
        user.setPassword(userDto.getPassword());

         */
        return user;
    }

    public UserDto userEntityToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;

        /*
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setRoleId(user.getRoleId());
        userDto.setEmailId(user.getEmailId());
        userDto.setPassword(user.getPassword());
        return userDto;

         */
    }
}
