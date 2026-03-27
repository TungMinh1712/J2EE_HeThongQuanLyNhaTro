package com.example.DACK_QuanLyNhaTro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "khach_thue")
public class KhachThue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_khach_thue")
    private String tenKhachThue;


}