package com.system.beverage_store.repo;

import com.system.beverage_store.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCartRepo extends JpaRepository<ProductCart, Integer> {
    @Query(value = "SELECT * FROM KOS_PRODUCTS_CARTS WHERE user_id = ?1", nativeQuery = true)
    Optional<List<ProductCart>> fetchAll(Integer userId);

    @Query(value = "select COUNT(*) from kos_products_carts", nativeQuery = true)
    Long countAllRows();
}
