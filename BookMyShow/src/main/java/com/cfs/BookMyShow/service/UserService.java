package com.cfs.BookMyShow.service;


import com.cfs.BookMyShow.dto.UserDto;
import com.cfs.BookMyShow.exception.ResourceNotFoundException;
import com.cfs.BookMyShow.model.User;
import com.cfs.BookMyShow.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    private UserDto createUser(UserDto userDto){
        User user = mapToEntity(userDto);
        User savedUser =  userRepository.save(user);
        return mapToDto(savedUser);
    }

    private User mapToEntity(UserDto userDto){
        User user =  new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }

    public UserDto getUserById(Long id){

        User user = userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User Not Found With Id: " + id));
        return mapToDto(user);

    }

    public List<UserDto> getAllUser(){
        List<User> users = userRepository.findAll();
    return users.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // update user
    // delete user

    public  UserDto updateUser(Long id, UserDto userDto){
        User user =  userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User Not Found With Id: " + id));
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(user);
        return mapToDto(updatedUser);
    }

    //delete user
    public  void deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User NOt Found With Id"+ id));
        userRepository.delete(user);
    }

    private  UserDto mapToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }
}
