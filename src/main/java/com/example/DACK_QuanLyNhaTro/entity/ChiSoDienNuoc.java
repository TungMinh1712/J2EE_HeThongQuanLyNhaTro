package com.example.DACK_QuanLyNhaTro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "chi_so_dien_nuoc",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_phong_thang_loai",
                        columnNames = {"phong_id", "thang_nam", "loai_dich_vu"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChiSoDienNuoc extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "phong_id", nullable = false)
    private Phong phong;

    @Column(name = "thang_nam", nullable = false, length = 7)
    private String thangNam; // ví dụ: 2026-03

    @Enumerated(EnumType.STRING)
    @Column(name = "loai_dich_vu", nullable = false, length = 20)
    private LoaiDichVu loaiDichVu;

    @Column(name = "chi_so_cu", nullable = false)
    private Integer chiSoCu;

    @Column(name = "chi_so_moi", nullable = false)
    private Integer chiSoMoi;

    @Column(name = "so_luong_tieu_thu", nullable = false)
    private Integer soLuongTieuThu;

    @Column(name = "don_gia", nullable = false, precision = 15, scale = 2)
    private BigDecimal donGia;

    @Column(name = "thanh_tien", nullable = false, precision = 15, scale = 2)
    private BigDecimal thanhTien;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    public enum LoaiDichVu {
        DIEN,
        NUOC
    }
}