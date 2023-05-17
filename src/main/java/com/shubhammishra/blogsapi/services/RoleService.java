package com.shubhammishra.blogsapi.services;

import com.shubhammishra.blogsapi.dto.RolesDto;

import java.util.List;

public interface RoleService {

    List<RolesDto> getAllRoles();

    RolesDto getRoleNameById(Integer roleId);


    RolesDto createRole(RolesDto rolesDto);
}
