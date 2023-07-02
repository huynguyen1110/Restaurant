package com.hivetech.ine2.htine2.model.storage.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "reviews_id", referencedColumnName = "id")
    private Reviews reviews;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
