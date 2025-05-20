# model Kontak.java

```java
package com.example.belajar_spring.model;

import java.time.LocalDate;

import com.example.belajar_spring.model.Kontak;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public abstract class Kontak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private String noTelp;
    private String email;
    private LocalDate tanggalDitambahkan;
    protected String jenisKontak;
    private String sumber;

    public Kontak() {
        // constructor default wajib untuk JPA
    }

    public Kontak(String nama, String noTelp, String email, LocalDate tanggalDitambahkan, String jenisKontak) {
        this.nama = nama;
        this.noTelp = noTelp;
        this.email = email;
        this.tanggalDitambahkan = tanggalDitambahkan;
        this.jenisKontak = jenisKontak;
    }

    // Getter dan Setter id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter dan Setter lain
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getTanggalDitambahkan() {
        return tanggalDitambahkan;
    }

    public void setTanggalDitambahkan(LocalDate tanggalDitambahkan) {
        this.tanggalDitambahkan = tanggalDitambahkan;
    }

    public String getJenisKontak() {
        return jenisKontak;
    }

    public void setJenisKontak(String jenisKontak) {
        this.jenisKontak = jenisKontak;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

}

---

## KontakService.java

```java
package com.example.belajar_spring.service;

import com.example.belajar_spring.model.Keluarga;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KontakService {
    private final List<Keluarga> kontakKeluargaList = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Keluarga> getAllKontak() {
        return kontakKeluargaList;
    }

    public List<Keluarga> getAllKontakSortedByName() {
        return kontakKeluargaList.stream()
                .sorted(Comparator.comparing(Keluarga::getNama, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    public List<Keluarga> searchKontak(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return kontakKeluargaList.stream()
                .filter(k -> k.getNama().toLowerCase().contains(lowerKeyword) ||
                        k.getNoTelp().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }

    public Keluarga saveKontak(Keluarga kontak) {
        if (kontak.getId() == null) {
            kontak.setId(idCounter++);
            kontakKeluargaList.add(kontak);
        }
        return kontak;
    }

    public Keluarga getKontakByID(Long id) {
        return kontakKeluargaList.stream()
                .filter(k -> k.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updateKontak(Keluarga kontak) {
        for (int i = 0; i < kontakKeluargaList.size(); i++) {
            if (kontakKeluargaList.get(i).getId().equals(kontak.getId())) {
                kontakKeluargaList.set(i, kontak);
                return;
            }
        }
    }

    public void deleteKontak(Long id) {
        kontakKeluargaList.removeIf(k -> k.getId().equals(id));
    }
}


