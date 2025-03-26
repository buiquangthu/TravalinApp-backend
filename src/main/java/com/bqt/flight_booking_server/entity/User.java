package com.bqt.flight_booking_server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // tự sinh id
    private String userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;

    private String fullname;


    public User(String email, String password, String phone, String fullname) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.fullname = fullname;
    }
}
