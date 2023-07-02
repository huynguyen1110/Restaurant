package com.hivetech.ine2.htine2.model.storage.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private Date createdDate;
    private Double rating;
    private String status;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "reviews", fetch = FetchType.LAZY)
    private List<Comment> commentList;
}
