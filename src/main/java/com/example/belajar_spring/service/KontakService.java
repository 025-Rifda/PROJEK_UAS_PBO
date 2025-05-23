package com.example.belajar_spring.service;

import com.example.belajar_spring.model.Kontak;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class KontakService {

    private final List<Kontak> semuaKontak = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // Tambah kontak baru
    public Kontak simpanKontak(Kontak kontak) {
        if (kontak.getId() == null) {
            kontak.setId(idCounter.getAndIncrement());
        }
        semuaKontak.add(kontak);
        return kontak;
    }

    // Ambil semua kontak
    public List<Kontak> getSemuaKontak() {
        return semuaKontak;
    }

    // Cari kontak berdasarkan ID
    public Kontak getKontakById(Long id) {
        return semuaKontak.stream()
                .filter(k -> k.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Update kontak
    public void updateKontak(Kontak kontak) {
        for (int i = 0; i < semuaKontak.size(); i++) {
            if (semuaKontak.get(i).getId().equals(kontak.getId())) {
                semuaKontak.set(i, kontak);
                return;
            }
        }
    }

    // Hapus kontak
    public void hapusKontak(Long id) {
        semuaKontak.removeIf(k -> k.getId().equals(id));
    }

    // Cari kontak berdasarkan keyword (nama, email, no telp)
    public List<Kontak> cariKontak(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return semuaKontak.stream()
                .filter(k -> (k.getNama() != null && k.getNama().toLowerCase().contains(lowerKeyword)) ||
                        (k.getEmail() != null && k.getEmail().toLowerCase().contains(lowerKeyword)) ||
                        (k.getNoTelp() != null && k.getNoTelp().contains(keyword)))
                .collect(Collectors.toList());
    }

    // Ambil kontak berdasarkan sumber (Keluarga, Teman, Kerja)
    public List<Kontak> getKontakBySumber(String sumber) {
        return semuaKontak.stream()
                .filter(k -> k.getSumber() != null && k.getSumber().equalsIgnoreCase(sumber))
                .collect(Collectors.toList());
    }
}
