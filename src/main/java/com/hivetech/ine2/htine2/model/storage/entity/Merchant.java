package com.hivetech.ine2.htine2.model.storage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String restaurantName;
    private String restaurantSlug;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private String address;
    private String about;
    private String status;
    private Integer idHeader;
    private Integer idLogo;
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;
    @OneToOne
    @JoinColumn(name = "login_infor_id")
    private LoginInformation loginInformation;
}
