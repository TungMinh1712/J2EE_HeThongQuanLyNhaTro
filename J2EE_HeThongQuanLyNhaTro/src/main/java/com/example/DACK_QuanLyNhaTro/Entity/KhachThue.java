package com.example.DACK_QuanLyNhaTro.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "khach_thue")
public class KhachThue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String maKhach;

    private String hoTen;
    private String soDienThoai;
    private String cmndCccd;
    private String queQuan;
    private String gioiTinh;
    private String anhDaiDien; // Lưu tên file ảnh

    // Giả sử bạn đã có Entity PhongTro
    // @ManyToOne
    // @JoinColumn(name = "phong_id")
    // private PhongTro phong;

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    // Bạn cũng nên tạo luôn hàm Get để sau này hiển thị ảnh
    public String getAnhDaiDien() {
        return anhDaiDien;
    }
}

