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
public class SalesPromotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer buyAmount;
    private Integer getAmount;
    private Integer status;
    @OneToOne
    @JoinColumn(name = "item_id")
    private Items items;
    private Date discountStart;
    private Date discountEnd;
}
