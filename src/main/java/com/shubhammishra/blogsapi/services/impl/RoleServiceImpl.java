package com.shubhammishra.blogsapi.services.impl;

import com.shubhammishra.blogsapi.dto.RolesDto;
import com.shubhammishra.blogsapi.entiities.Role;
import com.shubhammishra.blogsapi.exceptions.ResourceFoundException;
import com.shubhammishra.blogsapi.exceptions.ResourceNotFoundException;
import com.shubhammishra.blogsapi.repositories.RoleRepository;
import com.shubhammishra.blogsapi.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<RolesDto> getAllRoles() {
        List<Role> role = roleRepository.findAll();
        List<RolesDto> rolesDtoList = role.stream().map(role1 -> modelMapper.map(role1, RolesDto.class) ).collect(Collectors.toList());
        return rolesDtoList;
    }

    @Override
    public RolesDto getRoleNameById(Integer roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId.toString()));

        return modelMapper.map(role, RolesDto.class);
    }


    @Override
    public RolesDto createRole(RolesDto rolesDto){
        List<RolesDto> rolesDtoList = getAllRoles();

        int sizeArray  = (int) rolesDtoList.stream().filter(role -> role.getRoleName().equalsIgnoreCase(rolesDto.getRoleName())).count();
        if (sizeArray > 0) {
            throw  new ResourceFoundException("Role", "roleName", rolesDto.getRoleName());

        }else{
            Role role1 = modelMapper.map(rolesDto,Role.class);
            Role savedRole = roleRepository.save(role1);
            return modelMapper.map(savedRole, RolesDto.class);
        }

    }
}
