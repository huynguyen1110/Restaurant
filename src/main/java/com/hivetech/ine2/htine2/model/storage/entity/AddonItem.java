package com.hivetech.ine2.htine2.model.storage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddonItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "addon_category_id")
    private AddonCategory addonCategory;
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
    @Enumerated(EnumType.STRING)
    private Status status;
    private enum Status {
        DRAFT, PENDING_FOR_REVIEW, PUBLISH
    }
}
