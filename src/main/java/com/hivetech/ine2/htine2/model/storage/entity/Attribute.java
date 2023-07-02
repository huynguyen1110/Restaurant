package com.hivetech.ine2.htine2.model.storage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String status;
    @Enumerated(EnumType.STRING)
    private Type type;
    private enum Type {
        SIZE,
        INGREDIENT,
        COOKING_REFERENCE
    }
}
