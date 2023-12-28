package com.test.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String firstname;
    private String lastname;
    private String address;
    private String email;
    private String password;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updatedAt;
    @Enumerated(EnumType.STRING)
    private Role role;
}
