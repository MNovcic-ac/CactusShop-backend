package com.example.cactusshop.controller;

import com.example.cactusshop.dto.role.CreateRoleDto;
import com.example.cactusshop.dto.role.RoleResponseDto;
import com.example.cactusshop.dto.role.UpdateRoleDto;
import com.example.cactusshop.mapper.RoleMapper;
import com.example.cactusshop.service.RoleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
public class RolesController {

    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @GetMapping
    public List<RoleResponseDto> getRoles(){
        return roleMapper.mapToResponse(roleService.getRoles());
    }

    @GetMapping("/{uuid}")
    public RoleResponseDto getById(@PathVariable String uuid){
        return roleMapper.mapToResponse(roleService.getById(uuid));
    }

    @PostMapping
    public RoleResponseDto create(@RequestBody CreateRoleDto createRoleDto){
        return roleMapper.mapToResponse(roleService.create(createRoleDto));
    }

    @PutMapping("/{uuid}")
    public RoleResponseDto update(@RequestBody UpdateRoleDto updateRoleDto, @PathVariable String uuid){
        return roleMapper.mapToResponse(roleService.update(updateRoleDto, uuid));
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable String uuid){
        roleService.delete(uuid);
    }
}
