package com.tripease.entities;

import com.tripease.enums.Gender;
import com.tripease.security.Role;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String name;

    private int age;

    @Column(unique = true, nullable = false)
    private String email;

    @Column( nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role  role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    List<Booking> bookings =  new ArrayList<>();

}

