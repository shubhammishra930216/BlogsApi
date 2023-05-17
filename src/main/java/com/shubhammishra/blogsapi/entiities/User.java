package com.shubhammishra.blogsapi.entiities;

import com.shubhammishra.blogsapi.dto.RolesDto;
import com.shubhammishra.blogsapi.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="users",schema="blogs_api")
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity implements UserDetails,Serializable {



    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name="user_name",nullable = false,length = 100)
    @NotNull
    private String name;

    @Column(name = "email_id",nullable = false)
    @NotNull
    @NotBlank
    private String emailId;

    private String password;

    @Column(name ="role_id",nullable = false)
    private int roleId;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comments> comments = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name="user_role",
    joinColumns = @JoinColumn(name="users",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="role",referencedColumnName = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities= roles.stream().map(role1 -> new SimpleGrantedAuthority(role1.getRoleName())).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getUsername() {
        return emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
