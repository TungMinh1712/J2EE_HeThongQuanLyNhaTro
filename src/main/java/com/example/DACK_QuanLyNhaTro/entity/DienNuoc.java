package com.example.DACK_QuanLyNhaTro.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dien_nuoc")
public class DienNuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "phong_id", nullable = false)
    private Phong phong;

    @Column(nullable = false)
    private Integer thang;

    @Column(nullable = false)
    private Integer nam;

    @Column(name = "chi_so_dien_cu", nullable = false)
    private Integer chiSoDienCu;

    @Column(name = "chi_so_dien_moi", nullable = false)
    private Integer chiSoDienMoi;

    @Column(name = "chi_so_nuoc_cu", nullable = false)
    private Integer chiSoNuocCu;

    @Column(name = "chi_so_nuoc_moi", nullable = false)
    private Integer chiSoNuocMoi;

    @Column(name = "gia_dien", nullable = false)
    private BigDecimal giaDien;

    @Column(name = "gia_nuoc", nullable = false)
    private BigDecimal giaNuoc;

    @Column(name = "phi_gui_xe", nullable = false)
    private BigDecimal phiGuiXe;

    @Column(name = "phi_rac", nullable = false)
    private BigDecimal phiRac;

    @Column(name = "phi_internet", nullable = false)
    private BigDecimal phiInternet;

    @Column(name = "tong_tien_dien", nullable = false)
    private BigDecimal tongTienDien;

    @Column(name = "tong_tien_nuoc", nullable = false)
    private BigDecimal tongTienNuoc;

    @Column(name = "tong_tien_dich_vu", nullable = false)
    private BigDecimal tongTienDichVu;

    @Column(name = "tong_tien", nullable = false)
    private BigDecimal tongTien;

    public DienNuoc() {
    }

    public Long getId() {
        return id;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public Integer getThang() {
        return thang;
    }

    public void setThang(Integer thang) {
        this.thang = thang;
    }

    public Integer getNam() {
        return nam;
    }

    public void setNam(Integer nam) {
        this.nam = nam;
    }

    public Integer getChiSoDienCu() {
        return chiSoDienCu;
    }

    public void setChiSoDienCu(Integer chiSoDienCu) {
        this.chiSoDienCu = chiSoDienCu;
    }

    public Integer getChiSoDienMoi() {
        return chiSoDienMoi;
    }

    public void setChiSoDienMoi(Integer chiSoDienMoi) {
        this.chiSoDienMoi = chiSoDienMoi;
    }

    public Integer getChiSoNuocCu() {
        return chiSoNuocCu;
    }

    public void setChiSoNuocCu(Integer chiSoNuocCu) {
        this.chiSoNuocCu = chiSoNuocCu;
    }

    public Integer getChiSoNuocMoi() {
        return chiSoNuocMoi;
    }

    public void setChiSoNuocMoi(Integer chiSoNuocMoi) {
        this.chiSoNuocMoi = chiSoNuocMoi;
    }

    public BigDecimal getGiaDien() {
        return giaDien;
    }

    public void setGiaDien(BigDecimal giaDien) {
        this.giaDien = giaDien;
    }

    public BigDecimal getGiaNuoc() {
        return giaNuoc;
    }

    public void setGiaNuoc(BigDecimal giaNuoc) {
        this.giaNuoc = giaNuoc;
    }

    public BigDecimal getPhiGuiXe() {
        return phiGuiXe;
    }

    public void setPhiGuiXe(BigDecimal phiGuiXe) {
        this.phiGuiXe = phiGuiXe;
    }

    public BigDecimal getPhiRac() {
        return phiRac;
    }

    public void setPhiRac(BigDecimal phiRac) {
        this.phiRac = phiRac;
    }

    public BigDecimal getPhiInternet() {
        return phiInternet;
    }

    public void setPhiInternet(BigDecimal phiInternet) {
        this.phiInternet = phiInternet;
    }

    public BigDecimal getTongTienDien() {
        return tongTienDien;
    }

    public void setTongTienDien(BigDecimal tongTienDien) {
        this.tongTienDien = tongTienDien;
    }

    public BigDecimal getTongTienNuoc() {
        return tongTienNuoc;
    }

    public void setTongTienNuoc(BigDecimal tongTienNuoc) {
        this.tongTienNuoc = tongTienNuoc;
    }

    public BigDecimal getTongTienDichVu() {
        return tongTienDichVu;
    }

    public void setTongTienDichVu(BigDecimal tongTienDichVu) {
        this.tongTienDichVu = tongTienDichVu;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }
}