package com.linkhub.linkservice.service;

import com.linkhub.linkservice.model.User;
import com.linkhub.linkservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // 1. Sets up the Mockito testing framework
class UserServiceTest {

    @Mock // 2. Creates a FAKE version of our UserRepository
    private UserRepository userRepository;

    @InjectMocks // 3. Creates a REAL instance of UserService, but injects the FAKE repository into it
    private UserService userService;

    @Test // 4. Marks this method as a test case that JUnit should run
    void getAllUsers_shouldReturnListOfUsers() {
        // --- ARRANGE ---
        // 5. We define our fake data
        User user1 = User.builder().id(1L).username("test1").email("test1@email.com").build();
        User user2 = User.builder().id(2L).username("test2").email("test2@email.com").build();
        List<User> fakeUserList = List.of(user1, user2);

        // 6. We program our FAKE repository's behavior
        when(userRepository.findAll()).thenReturn(fakeUserList);

        // --- ACT ---
        // 7. We call the real method on our UserService that we want to test
        List<User> result = userService.getAllUsers();

        // --- ASSERT ---
        // 8. We check if the result is what we expected
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getUsername()).isEqualTo("test1");
    }
}