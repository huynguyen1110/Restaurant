package com.hivetech.ine2.htine2.model.storage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private double price;
    private double promoPrice;
    private String status;
    private String monthly;
    private int itemLimit;
    private int trialPeriod ;
    private int orderLimit;
    private boolean ordering;
    @OneToOne
    @JoinColumn(name = "descriptionId", referencedColumnName = "id")
    private Description description;
}
