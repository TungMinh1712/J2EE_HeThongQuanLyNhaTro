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
    private Integer id;

    private String maPhong;

    private String tenPhong;

    private Double gia;

    private Float dienTich;

    private Integer soLuong;

    @Enumerated(EnumType.STRING)
    private TrangThai trangThai;

    private String anh;

    private Integer userId;

    // ENUM viết ngay trong class
    public enum TrangThai {
        CON_TRONG,
        DA_THUE
    }
}