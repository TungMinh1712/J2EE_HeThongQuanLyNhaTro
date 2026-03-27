package com.example.DACK_QuanLyNhaTro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hop_dong")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HopDong extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "phong_id", nullable = false)
    private Phong phong;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "khach_thue_id", nullable = false)
    private KhachThue khachThue;

    @Column(name = "ngay_bat_dau", nullable = false)
    private LocalDate ngayBatDau;

    @Column(name = "ngay_ket_thuc", nullable = false)
    private LocalDate ngayKetThuc;

    @Column(name = "gia_thue_luc_ky", nullable = false, precision = 15, scale = 2)
    private BigDecimal giaThueLucKy;

    @Column(name = "tien_coc", nullable = false, precision = 15, scale = 2)
    private BigDecimal tienCoc;

    @Column(name = "dieu_khoan", length = 500)
    private String dieuKhoan;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false, length = 20)
    private TrangThaiHopDong trangThai;

    @OneToMany(mappedBy = "hopDong", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<HoaDon> danhSachHoaDon = new ArrayList<>();

    public enum TrangThaiHopDong {
        CON_HIEU_LUC,
        HET_HAN,
        DA_HUY
    }
}