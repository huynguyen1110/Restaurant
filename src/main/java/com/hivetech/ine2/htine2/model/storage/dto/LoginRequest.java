package com.hivetech.ine2.htine2.model.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    
    @NotEmpty()
    private String userName;
    @NotEmpty()
    private String password;
}
