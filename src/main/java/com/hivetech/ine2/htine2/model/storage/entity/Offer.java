package com.hivetech.ine2.htine2.model.storage.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer percentage;
    private Double orderOver;
    private Date validFrom;
    private Date validTo;
    private String applicable;
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
