package com.shubhammishra.blogsapi.entiities;

import com.shubhammishra.blogsapi.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users",schema="blogs_api")
@NoArgsConstructor
@Getter
@Setter
public class User {



    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name="user_name",nullable = false,length = 100)
    @NotNull
    private String name;

    @Column(name = "email_id",nullable = false)
    private String emailId;

    private String password;

    @Column(name ="role_id",nullable = false)
    private int roleId;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comments> comments = new ArrayList<>();


}
