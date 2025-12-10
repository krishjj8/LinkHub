package com.linkhub.linkservice.service;

import com.linkhub.linkservice.dto.UserCreateRequestDto;
import com.linkhub.linkservice.dto.UserUpdateRequestDto;
import com.linkhub.linkservice.model.User;
import com.linkhub.linkservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import com.linkhub.linkservice.dto.ProfileResponseDto;
import com.linkhub.linkservice.dto.LinkDto;
import com.linkhub.linkservice.model.Link;
import com.linkhub.linkservice.repository.LinkRepository;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LinkRepository linkRepository;

    public UserService(UserRepository userRepository, LinkRepository linkRepository){
        this.userRepository = userRepository;
        this.linkRepository = linkRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createUser(UserCreateRequestDto userDto){
        User newuser = User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
        return userRepository.save(newuser);
    }

    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id:" + id));
    }

    public User updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto){
        User existinguser = getUserById(id);

        if(userUpdateRequestDto.getUsername() != null){
            existinguser.setUsername(userUpdateRequestDto.getUsername());
        }
        if(userUpdateRequestDto.getEmail() != null){
            existinguser.setEmail(userUpdateRequestDto.getEmail());
        }
        return userRepository.save(existinguser);
    }

    public void deleteUser(Long id){
        User userToDelete = getUserById(id);
        userRepository.deleteById(userToDelete.getId());
    }

    @Cacheable(value = "profiles", key = "#username")
    public ProfileResponseDto getPublicProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        List<Link> links = linkRepository.findByUserId(user.getId());

        List<LinkDto> linkDtos = links.stream()
                .map(link -> new LinkDto(link.getId(), link.getTitle(), link.getUrl()))
                .collect(Collectors.toList());

        return new ProfileResponseDto(user.getUsername(), "Welcome to my LinkHub!", linkDtos);
    }
}