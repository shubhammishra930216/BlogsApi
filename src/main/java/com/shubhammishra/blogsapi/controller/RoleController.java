package com.shubhammishra.blogsapi.controller;

import com.shubhammishra.blogsapi.dto.RolesDto;
import com.shubhammishra.blogsapi.services.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping("/roles/")
    public ResponseEntity<List<RolesDto>> getAllRoles(){
        List<RolesDto> rolesDtoList = roleService.getAllRoles();
        return new ResponseEntity<>(rolesDtoList, HttpStatus.OK);
    }

    @GetMapping("/roles/{roleId}")
    public ResponseEntity<RolesDto> getRolesById(@RequestBody @PathVariable(name="roleId",required = true) Integer roleId){
        RolesDto rolesDto = roleService.getRoleNameById(roleId);
        return new ResponseEntity<>(rolesDto,HttpStatus.FOUND);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/roles/")
    public ResponseEntity<RolesDto> createRoles(@RequestBody RolesDto rolesDto){
        RolesDto rolesDto1 = roleService.createRole(rolesDto);
        return new ResponseEntity<>(rolesDto1,HttpStatus.CREATED);

    }
}
