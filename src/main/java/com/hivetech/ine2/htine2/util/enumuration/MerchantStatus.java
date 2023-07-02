package com.hivetech.ine2.htine2.util.enumuration;

import lombok.Getter;

@Getter
public enum MerchantStatus {
    ACTIVE("ACTIVE"), PENDING_FOR_APPROVAL("PENDING FOR APPROVAL"), EXPIRED("EXPIRED");

    private final String description;

    MerchantStatus(String description) {
        this.description = description;
    }
}
