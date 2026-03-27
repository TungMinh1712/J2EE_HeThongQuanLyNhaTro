package com.example.DACK_QuanLyNhaTro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hoa_don")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDon extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "phong_id", nullable = false)
    private Phong phong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hop_dong_id")
    private HopDong hopDong;

    @Column(name = "thang_lap", nullable = false, length = 7)
    private String thangLap; // ví dụ: 2026-03

    @Column(name = "ngay_lap", nullable = false)
    private LocalDate ngayLap;

    @Column(name = "han_thanh_toan", nullable = false)
    private LocalDate hanThanhToan;

    @Column(name = "tien_phong", nullable = false, precision = 15, scale = 2)
    private BigDecimal tienPhong;

    @Column(name = "tien_dien", nullable = false, precision = 15, scale = 2)
    private BigDecimal tienDien;

    @Column(name = "tien_nuoc", nullable = false, precision = 15, scale = 2)
    private BigDecimal tienNuoc;

    @Column(name = "chi_phi_khac", nullable = false, precision = 15, scale = 2)
    private BigDecimal chiPhiKhac;

    @Column(name = "giam_gia", nullable = false, precision = 15, scale = 2)
    private BigDecimal giamGia;

    @Column(name = "tong_tien", nullable = false, precision = 15, scale = 2)
    private BigDecimal tongTien;

    @Column(name = "so_tien_da_thanh_toan", nullable = false, precision = 15, scale = 2)
    private BigDecimal soTienDaThanhToan;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false, length = 20)
    private TrangThaiHoaDon trangThai;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ChiTietHoaDon> danhSachChiTiet = new ArrayList<>();

    public enum TrangThaiHoaDon {
        CHUA_THANH_TOAN,
        THANH_TOAN_MOT_PHAN,
        DA_THANH_TOAN,
        DA_HUY
    }
}