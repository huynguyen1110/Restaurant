package com.hivetech.ine2.htine2.model.storage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private Double price;
    @OneToOne
    @JoinColumn(name = "size_id")
    private Attribute size;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Enumerated(EnumType.STRING)
    private Featured featured;
    private enum Featured {
        NEW_ITEMS, TRENDING, BEST_SELLER, RECOMMENDED
    }
    @Enumerated(EnumType.STRING)
    private Status status;
    private enum Status {
        DRAFT, PENDING_FOR_REVIEW, PUBLISH
    }
}
