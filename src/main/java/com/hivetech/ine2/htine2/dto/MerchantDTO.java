package com.hivetech.ine2.htine2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MerchantDTO {
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
}
