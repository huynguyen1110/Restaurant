package com.hivetech.ine2.htine2.model.storage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String metaTitle;
    private String metaDiscription;
    private String keywords;
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

}
