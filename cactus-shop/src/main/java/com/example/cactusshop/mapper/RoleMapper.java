package com.example.cactusshop.mapper;

import com.example.cactusshop.dto.role.CreateRoleDto;
import com.example.cactusshop.dto.role.RoleResponseDto;
import com.example.cactusshop.entity.Role;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public abstract class RoleMapper {

    @Named("mapToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "roleName")
    @Mapping(target = "roleDescription")
    public abstract Role mapToEntity(CreateRoleDto createRoleDto);

    @Named("mapToResponse")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "roleName")
    @Mapping(target = "roleDescription")
    public abstract RoleResponseDto mapToResponse(Role role);

    @IterableMapping(qualifiedByName = "mapToResponse")
    public abstract List<RoleResponseDto> mapToResponse(List<Role> roles);
}
