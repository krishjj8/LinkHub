package com.linkhub.linkservice.controller;


import com.linkhub.linkservice.dto.UserCreateRequestDto;
import com.linkhub.linkservice.dto.UserResponseDto;
import com.linkhub.linkservice.dto.UserUpdateRequestDto;
import com.linkhub.linkservice.model.User;
import com.linkhub.linkservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping
    public List<UserResponseDto> getAllUser(){
        List<User> users = userService.getAllUsers();
        List<UserResponseDto> userResponseDtos=new ArrayList<>();
        for(User user:users){
            UserResponseDto Dto=new UserResponseDto();
            Dto.setId(user.getId());
            Dto.setUsername(user.getUsername());
            Dto.setEmail(user.getEmail());
            userResponseDtos.add(Dto);

        }
        return userResponseDtos;
    }
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateRequestDto userDto){
        User newuser= userService.createUser(userDto);
        UserResponseDto dto=new UserResponseDto();
        dto.setId(newuser.getId());
        dto.setUsername(newuser.getUsername());
        dto.setEmail(newuser.getEmail());
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        User user=userService.getUserById(id);
        UserResponseDto responseDto=new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setUsername(user.getUsername());
        responseDto.setEmail(user.getEmail());

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequestDto userUpdateRequestDto){
        User updateduser=userService.updateUser(id,userUpdateRequestDto);

        UserResponseDto responseDto=new UserResponseDto();
        responseDto.setId(updateduser.getId());
        responseDto.setUsername(updateduser.getUsername());
        responseDto.setEmail(updateduser.getEmail());

        return ResponseEntity.ok(responseDto);
    }



}
