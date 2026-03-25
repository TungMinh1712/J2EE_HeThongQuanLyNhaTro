package com.example.DACK_QuanLyNhaTro.service;

import com.example.DACK_QuanLyNhaTro.entity.Phong;
import com.example.DACK_QuanLyNhaTro.repository.PhongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongService {

    @Autowired
    private PhongRepository repo;

    public List<Phong> getAll() {
        return repo.findAll();
    }

    public Phong save(Phong p) {
        return repo.save(p);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public Phong getById(Integer id) {
        return repo.findById(id).orElse(null);
    }
}