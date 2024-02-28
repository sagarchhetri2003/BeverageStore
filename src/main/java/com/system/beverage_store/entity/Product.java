package com.system.beverage_store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kos_products")
public class Product {
    @Id
    @SequenceGenerator(name = "kos_products_seq_gen", sequenceName = "kos_products_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "kos_products_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String photo;
    @Column(name = "productName", nullable = false)
    private String name;
    @Column(name = "productQuantity", nullable = false)
    private String quantity;
    @Column(name = "productPrice", nullable = false)
    private String price;
    @Transient
    private String imageBase64;
}
