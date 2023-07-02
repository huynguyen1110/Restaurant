package com.hivetech.ine2.htine2.model.storage.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.TimeZone;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="log_info")
public class LogInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String username;
    private String userAgent;
    private String ipAddress;
    private String action;
    TimeZone timeZone;
}
