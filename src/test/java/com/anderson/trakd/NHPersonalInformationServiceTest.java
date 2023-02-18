package com.anderson.trakd;

import com.anderson.trakd.model.User;
import com.anderson.trakd.repository.UserRepository;
import com.anderson.trakd.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;
@SpringBootTest
public class NHPersonalInformationServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testCreateNHPersonal() throws Exception {
        User expected = new User("test000", "test000@test.com", "password");
        userService.createUser(expected);
        User actual = userRepository.findByEmail("test000@test.com");
        assertEquals(expected, actual);

    }
}
