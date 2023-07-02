package com.hivetech.ine2.htine2.model.storage.entity;

import com.hivetech.ine2.htine2.util.enumuration.Status;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "First Name cannot be blank.")
    private String firstName;
    @NotEmpty(message = "Last Name cannot be blank.")
    private String lastName;
    @NotEmpty(message = "Email address cannot be blank..")
    private String email;
    private String phone;
    @Column(name = "user_name")
    @NotEmpty(message = "Username cannot be blank.")
    private String userName;
    @NotEmpty(message = "password cannot be blank.")
    private String password;
    @Enumerated(EnumType.STRING)
    private ERole roles;
    @Enumerated(EnumType.STRING)
    private Status status;

}

