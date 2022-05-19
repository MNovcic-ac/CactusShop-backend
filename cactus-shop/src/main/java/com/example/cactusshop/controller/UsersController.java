package com.example.cactusshop.controller;

import com.example.cactusshop.dto.user.CreateUserDto;
import com.example.cactusshop.dto.user.UpdateUserDto;
import com.example.cactusshop.dto.user.UserResponseDto;
import com.example.cactusshop.mapper.UserMapper;
import com.example.cactusshop.security.SecurityService;
import com.example.cactusshop.service.UserService;
import java.io.UnsupportedEncodingException;
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
@RequestMapping("/api/users")
public class UsersController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserResponseDto create(@RequestBody CreateUserDto createUserDto){
        return userMapper.mapToResponse(userService.create(createUserDto));
    }

    @PutMapping("/{uuid}")
    public UserResponseDto update(@RequestBody UpdateUserDto updateUserDto, @PathVariable String uuid, @PathVariable String token) throws UnsupportedEncodingException {
        return userMapper.mapToResponse(userService.updateUser(updateUserDto, uuid));
    }

    @GetMapping("/{uuid}")
    public UserResponseDto getById(@PathVariable String uuid){
        return userMapper.mapToResponse(userService.getUserById(uuid));
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable String uuid){
        userService.deleteUser(uuid);
    }

    @GetMapping
    public List<UserResponseDto> getUsers(){
        return userMapper.mapToResponse(userService.getUsers());
    }
}
