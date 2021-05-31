package com.groupwork.pharmacy.management.system.userRepositoryTest;

import com.groupwork.pharmacy.management.system.model.User;
import com.groupwork.pharmacy.management.system.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        User user= new User();
        user.setEmail("jkanold@gmail.com");
        user.setPassword("Arnold24");
        user.setFirstName("Jeff");
        user.setLastName("Arnold");

        User savedUser= repo.save(user);

        User existUser= entityManager.find(User.class,savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());


    }

    @Test
    public void testFindUserByEmail(){
        String email="jkanold@gmail.com";

        User user=repo.findByEmail(email);
        assertThat(user).isNotNull();

    }
}
