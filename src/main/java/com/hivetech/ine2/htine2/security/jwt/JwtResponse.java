package com.hivetech.ine2.htine2.security.jwt;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String userName;
    private String password;
    private List<String> roles;

    public JwtResponse(String token, Integer id, String userName, String password, List<String> roles) {
        this.token = token;
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }
}