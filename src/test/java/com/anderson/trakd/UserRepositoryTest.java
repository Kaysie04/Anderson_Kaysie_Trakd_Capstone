package com.anderson.trakd;

import com.anderson.trakd.model.User;
import com.anderson.trakd.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;



@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindByEmail(){

        String email = "test2345@test.com";
        User expected = new User("test", email, "password");
        userRepository.save(expected);

        User actual = userRepository.findByEmail(email);
        assertEquals(expected, actual);
    }


}
