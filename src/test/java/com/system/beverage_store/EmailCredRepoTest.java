package com.system.beverage_store;

import com.system.beverage_store.entity.EmailCredentials;
import com.system.beverage_store.repo.EmailCredRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmailCredRepoTest {

    @Autowired
    private EmailCredRepo emailCredRepo;

    @Test
    @Order(1)
    @Transactional
    @Rollback(value = false)
    public void saveEmailCredentials() {
        EmailCredentials credentials = EmailCredentials.builder()
                .email("test@example.com")
                .password("password123")
                .host("smtp.example.com")
                .port("587")
                .active(true)
                .protocol("smtp")
                .build();

        credentials = emailCredRepo.save(credentials);
        Assertions.assertThat(credentials.getId()).isNotNull();
    }
}


