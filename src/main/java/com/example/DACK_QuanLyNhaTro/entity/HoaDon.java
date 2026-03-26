package entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hoadon")
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenNguoiThue;
    private double soTien;
    private String trangThai;

    private LocalDate ngayTao;
    private LocalDate ngayThanhToan;

    // Getter & Setter
    public Long getId() { return id; }

    public String getTenNguoiThue() { return tenNguoiThue; }
    public void setTenNguoiThue(String tenNguoiThue) { this.tenNguoiThue = tenNguoiThue; }

    public double getSoTien() { return soTien; }
    public void setSoTien(double soTien) { this.soTien = soTien; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public LocalDate getNgayTao() { return ngayTao; }
    public void setNgayTao(LocalDate ngayTao) { this.ngayTao = ngayTao; }

    public LocalDate getNgayThanhToan() { return ngayThanhToan; }
    public void setNgayThanhToan(LocalDate ngayThanhToan) { this.ngayThanhToan = ngayThanhToan; }
}