package com.linkhub.linkservice.service;


import com.linkhub.linkservice.dto.UserCreateRequestDto;
import com.linkhub.linkservice.dto.UserUpdateRequestDto;
import com.linkhub.linkservice.model.User;
import com.linkhub.linkservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;

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
    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {

        System.out.println("Fetching User " + id + " from Database...");

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id:" + id));
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
    public void deleteUser(Long id){
        User userToDelete=getUserById(id);

        userRepository.deleteById(userToDelete.getId());
    }


}
