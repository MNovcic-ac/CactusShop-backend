package com.example.cactusshop.service;

import com.example.cactusshop.constants.ErrorCodes;
import com.example.cactusshop.dto.role.CreateRoleDto;
import com.example.cactusshop.dto.role.UpdateRoleDto;
import com.example.cactusshop.entity.Role;
import com.example.cactusshop.exception.ConflictException;
import com.example.cactusshop.exception.NotFoundException;
import com.example.cactusshop.mapper.RoleMapper;
import com.example.cactusshop.repository.RoleRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public Role getById(String uuid){
        return roleRepository.findById(uuid).orElseThrow(() -> {
            log.info("Role with id '{}' does not exist.", uuid);
            throw new NotFoundException(ErrorCodes.ROLE_NOT_FOUND_1);
        });
    }

    public Role getById(String uuid, String errorCode){
        return roleRepository.findById(uuid).orElseThrow(() -> {
            log.info("Role with id '{}' does not exist.", uuid);
            throw new NotFoundException(errorCode);
        });
    }

    public Role create(CreateRoleDto createRoleDto){
        if(roleRepository.findRoleByRoleName(createRoleDto.getRoleName()) != null)
            throw new ConflictException(ErrorCodes.ROLE_FOUND);
        Role created = roleMapper.mapToEntity(createRoleDto);
        log.info("Successfully created new role {}", created.toString());

        return roleRepository.save(created);
    }

    public Role update(UpdateRoleDto updateRoleDto, String uuid){
        Role foundRole = getById(uuid, ErrorCodes.ROLE_NOT_FOUND_2);
        foundRole = buildUpdatedRole(foundRole, updateRoleDto);

        log.info("Role successfully updated!");
        return roleRepository.save(foundRole);
    }

    private Role buildUpdatedRole(Role update, UpdateRoleDto updateRoleDto) {
        update.setRoleName(updateRoleDto.getRoleName());
        update.setRoleDescription(updateRoleDto.getRoleDescription());

        return update;
    }

    public void delete(String uuid){
        Role foundRole = getById(uuid, ErrorCodes.ROLE_NOT_FOUND_3);

        log.info("Role successfully deleted!");
        roleRepository.delete(foundRole);
    }
}
