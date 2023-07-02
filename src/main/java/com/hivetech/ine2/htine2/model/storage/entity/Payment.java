package com.hivetech.ine2.htine2.model.storage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String paymentCode;
    private String paymentName;
    private String logoType;
    private String logoClassIcon;
    private String image;
    private Boolean status;
    private Boolean onlinePayment;
    private Boolean availableForPayout;
}
