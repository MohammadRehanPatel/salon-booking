package com.ab.model;

import com.ab.payload.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ElementCollection
    private List<String> images;
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    private String city;
    @Column(nullable = false)
    private Long ownerId;
    @Column(nullable = false)
    private LocalTime openTime;
    @Column(nullable = false)
    private LocalTime closeTime;



}
