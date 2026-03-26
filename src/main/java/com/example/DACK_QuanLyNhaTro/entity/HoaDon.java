package com.example.DACK_QuanLyNhaTro.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phong;
    private Integer thang;
    private Integer nam;

    private Double tienPhong = 0.0;
    private Double tienDien = 0.0;
    private Double tienNuoc = 0.0;
    private Double tienDichVu = 0.0;
    private Double tongTien = 0.0;

    // Thống nhất dùng mã: CHUA_THANH_TOAN hoặc DA_THANH_TOAN
    private String trangThai = "CHUA_THANH_TOAN";

    private String stk = "0123456789";
    private String nganHang = "MB Bank";
    private String noiDungCK;
    private LocalDate ngayTao = LocalDate.now();
}