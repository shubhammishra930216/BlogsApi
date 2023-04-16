package com.shubhammishra.blogsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Serializable {

    private long id;

    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be blank")
    private String name;

    @Email(message = "Email provided is not valid")
    @NotBlank(message = "Email cannot be blank")
    private String emailId;
    //private int roleId;

    @NotNull(message = "password cannot be null")
    @Size(min = 6,message = "Password size should not be less than 6")
    private String password;
}
