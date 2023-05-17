package com.shubhammishra.blogsapi.controller;

import com.shubhammishra.blogsapi.dto.UserDto;
import com.shubhammishra.blogsapi.payloads.ApiResponse;
import com.shubhammishra.blogsapi.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        UserDto userDto = userService.createUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);

    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable(value = "id" ) Long id){

            UserDto userDto = userService.getUserById(id);
            return new ResponseEntity<>(userDto, HttpStatus.FOUND);


    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getUser(){
        List<UserDto> userDtos = userService.getAllUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserById(@Valid @RequestBody UserDto user,@PathVariable(value = "id" ) Long id){
        UserDto userDto = userService.updateUser(user,id);
        return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "id" ) Long id){
        userService.deleteUsers(id);
        return new ResponseEntity<>(new ApiResponse("Successfully deleted user with id :"+id,true),HttpStatus.OK);

    }



}
