package com.system.beverage_store.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kos_notifications")
public class Notifications {
    @Id
    @SequenceGenerator(name = "kos_notifications_seq_gen", sequenceName = "kos_notifications_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "kos_notifications_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "topic")
    private String topic;
    @Column(name = "description")
    private String description;


}
