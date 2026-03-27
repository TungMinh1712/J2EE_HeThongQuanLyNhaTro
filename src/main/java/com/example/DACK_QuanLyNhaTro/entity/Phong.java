package com.example.DACK_QuanLyNhaTro.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phong")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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