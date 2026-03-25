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

    public Phong() {
    }

    public Long getId() {
        return id;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }
}