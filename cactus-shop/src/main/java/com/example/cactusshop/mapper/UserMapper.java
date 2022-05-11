package com.example.cactusshop.mapper;

import com.example.cactusshop.dto.user.CreateUserDto;
import com.example.cactusshop.dto.user.UserResponseDto;
import com.example.cactusshop.entity.User;
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
public abstract class UserMapper {

    @Named("mapToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "firstName")
    @Mapping(target = "lastName")
    @Mapping(target = "contactEmail")
    @Mapping(target = "contactPhone")
    @Mapping(target = "password")
    public abstract User mapToEntity(CreateUserDto createUserDto);

    @Named("mapToResponse")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    @Mapping(target = "contactEmail")
    @Mapping(target = "contactPhone")
    @Mapping(target = "uuid")
    public abstract UserResponseDto mapToResponse(User user);

    @IterableMapping(qualifiedByName = "mapToResponse")
    public abstract List<UserResponseDto> mapToResponse(List<User> users);
}
