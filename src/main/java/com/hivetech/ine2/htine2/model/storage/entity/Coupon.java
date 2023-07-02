package com.hivetech.ine2.htine2.model.storage.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;
    private Double discount;
    private Double minOrder;
    private String dayAvailable;
    private Date expiration;
    private String option;
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
