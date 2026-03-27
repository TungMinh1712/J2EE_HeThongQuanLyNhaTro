package com.example.DACK_QuanLyNhaTro.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.DACK_QuanLyNhaTro.Entity.KhachThue;
import com.example.DACK_QuanLyNhaTro.repository.KhachThueRepository;


@Service
public class KhachThueService {
    @Autowired
    private KhachThueRepository repository;

    private final Path root = Paths.get("src/main/resources/static/images/khach");

    public List<KhachThue> getAll() { return repository.findAll(); }

    public void save(KhachThue khach, MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                // Tạo thư mục nếu chưa có
                if (!Files.exists(root)) Files.createDirectories(root);
                
                // Đổi tên file để tránh trùng (VD: 123-abc.jpg)
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Files.copy(file.getInputStream(), this.root.resolve(fileName));
                khach.setAnhDaiDien(fileName);
            }
            repository.save(khach);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi lưu file: " + e.getMessage());
        }
    }

    public void delete(Long id) { repository.deleteById(id); }
    
    public KhachThue getById(Long id) { return (KhachThue) repository.findById(id).orElse(null); }
}