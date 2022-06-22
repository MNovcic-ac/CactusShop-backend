package com.example.cactusshop.service;

import com.example.cactusshop.dto.user.CreateUserDto;
import com.example.cactusshop.dto.user.UpdateUserDto;
import com.example.cactusshop.entity.User;
import com.example.cactusshop.mapper.UserMapper;
import com.example.cactusshop.repository.UserRepository;
import java.lang.module.FindException;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public User create(CreateUserDto createUserDto){
        if(findByEmail(createUserDto.getContactEmail()) != null)
            throw new FindException("User with that email already exists!");

        User created = userMapper.mapToEntity(createUserDto);
        created.setPassword(passwordEncoder.encode(created.getPassword()));
        created.setRole(roleService.getById("2881691c-14d1-4200-99d4-5d39b19b5be1"));

        log.info("User successfully registered {}", created.toString());
        return userRepository.save(created);
    }

    public User updateUser(UpdateUserDto updateUserDto, String uuid){
        Optional<User> foundUser = userRepository.findById(uuid);
        if(foundUser.isEmpty())
            throw new EntityNotFoundException("User with that id does not exist!");

        User update = foundUser.get();

        update = buildUpdatedUser(update, updateUserDto);

        log.info("User successfully updated!");
        return userRepository.save(update);
    }

    private User buildUpdatedUser(User user, UpdateUserDto updateUserDto){
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setContactEmail(updateUserDto.getContactEmail());
        user.setContactPhone(updateUserDto.getContactPhone());
        user.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));

        return user;
    }

    public void deleteUser(String uuid){
        Optional<User> foundUser = userRepository.findById(uuid);
        if(foundUser.isEmpty())
            throw new EntityNotFoundException("User with that id does not exist!");

        log.info("User successfully deleted!");
        userRepository.delete(foundUser.get());
    }

    public User getUserById(String uuid){
        Optional<User> foundUser = userRepository.findById(uuid);
        if(foundUser.isEmpty())
            throw new EntityNotFoundException("User with that id does not exist!");

        return foundUser.get();
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User findByEmail(String email){
        return userRepository.findByContactEmail(email);
    }
}
