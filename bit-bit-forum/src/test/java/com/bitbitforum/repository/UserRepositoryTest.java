package com.bitbitforum.repository;

import com.bitbitforum.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByEmail() {
        User user = new User();
        user.setLogin("test1");
        user.setUsername("test2");
        user.setPasswd("1233445");
        user.setRoleUsr("ROLE_USER");
        user.setAgeUsr(22);
        entityManager.persistAndFlush(user);

        Optional<User> found = userRepository.findByLogin("test1");

        assertThat(found.isPresent());
        assertThat(found.get().getAgeUsr()).isEqualTo(22);
        assertThat(found.get().getUsername()).isEqualTo("test2");
        assertThat(found.get().getRoleUsr()).isEqualTo("ROLE_USER");

    }
}
