package com.system.beverage_store;

import com.system.beverage_store.entity.Product;
import com.system.beverage_store.entity.ProductCart;
import com.system.beverage_store.entity.User;
import com.system.beverage_store.repo.ProductCartRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductCartRepoTest {

    @Autowired
    private ProductCartRepo productCartRepo;


    @Test
    @Order(1)
    public void countAllRows() {
        long count = productCartRepo.countAllRows();
        Assertions.assertThat(count).isGreaterThanOrEqualTo(0);
    }
}
