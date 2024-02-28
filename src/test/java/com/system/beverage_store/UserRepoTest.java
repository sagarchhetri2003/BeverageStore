package com.system.beverage_store;

import com.system.beverage_store.entity.Role;
import com.system.beverage_store.entity.User;
import com.system.beverage_store.repo.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;



    @Test
    @Order(1)
    public void countAllRows() {
        long count = userRepo.countAllRows();
        Assertions.assertThat(count).isGreaterThanOrEqualTo(0);
    }

    private User createUserWithRoles() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setNumber("1234567890");
        user.setAddress("123 Street, City");
        user.setPassword("password");
        user.setOTP("123456");

        // Create roles
        Role role1 = new Role();
        role1.setName("ROLE_USER");
        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");

        // Set roles
        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);
        user.setRoles(roles);

        return user;
    }
}
