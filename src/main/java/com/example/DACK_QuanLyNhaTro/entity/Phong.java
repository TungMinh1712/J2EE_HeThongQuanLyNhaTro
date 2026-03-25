package com.example.DACK_QuanLyNhaTro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "phong")
public class Phong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_phong")
    private String tenPhong;

}