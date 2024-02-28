//package com.system.beverage_store.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Builder
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "kos_queries")
//public class Queries {
//    @Id
//    @SequenceGenerator(name = "kos_queries_seq_gen", sequenceName = "kos_queries_id_seq", allocationSize = 1)
//    @GeneratedValue(generator = "kos_queries_seq_gen", strategy = GenerationType.SEQUENCE)
//    private Integer id;
//    @Column(name = "Name", nullable = false)
//    private String name;
//    @Column(name = "E-mail", nullable = false)
//    private String email;
//    @Column(name = "Subject", nullable = false)
//    private String subject;
//    @Column(name = "Message", nullable = false)
//    private String message;
//
//}
package com.system.beverage_store.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kos_queries")
public class Queries {
    @Id
    @SequenceGenerator(name = "kos_queries_seq_gen", sequenceName = "kos_queries_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "kos_queries_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "E-mail", nullable = false)
    private String email;
    @Column(name = "Subject", nullable = false)
    private String subject;
    @Column(name = "Message", nullable = false)
    private String message;

    public Queries(String name, String email, String subject, String message) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }
}
