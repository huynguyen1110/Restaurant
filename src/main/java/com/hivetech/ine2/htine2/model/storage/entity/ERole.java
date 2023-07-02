package com.hivetech.ine2.htine2.model.storage.entity;

import jakarta.persistence.EnumType;

public enum ERole {
    USER("USER"),
    ADMIN("ADMIN"),
    MERCHANT("MERCHANT");

    private String shortName;

    ERole(String shortName) {
        this.shortName = shortName;
    }

    public static ERole getRoleByShortName(String shortName) {
        switch (shortName) {
            case "USER": {
                return USER;
            }
            case "ADMIN" : {
                return ADMIN;
            }
            case "MERCHANT" : {
                return MERCHANT;
            }
            default: {
                throw new RuntimeException("Role not found");
            }
        }
    }
}