package com.linkhub.linkservice.service;


import com.linkhub.linkservice.dto.UserCreateRequestDto;
import com.linkhub.linkservice.dto.UserUpdateRequestDto;
import com.linkhub.linkservice.model.User;
import com.linkhub.linkservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){

        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){

        return userRepository.findAll();
    }
    public User createUser(UserCreateRequestDto userDto){
        User newuser=User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
        return userRepository.save(newuser);
    }
    public User getUserById(Long id){
        Optional<User> user= userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        else{
            throw new RuntimeException("User not found with id:" +id);
        }
    }

    public User updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto){
        User existinguser=getUserById(id);

        if(userUpdateRequestDto.getUsername()!=null){
            existinguser.setUsername(userUpdateRequestDto.getUsername());

        }
        if(userUpdateRequestDto.getEmail()!=null){
            existinguser.setEmail(userUpdateRequestDto.getEmail());

        }
        return userRepository.save(existinguser);
    }


}
