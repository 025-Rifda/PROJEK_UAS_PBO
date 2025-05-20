# ModelKontak.java

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
```

# KontakService.java

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
```

# KontakController.java

```java
package com.example.belajar_spring.controller;

import com.example.belajar_spring.model.Kontak;
import com.example.belajar_spring.service.KeluargaService;
import com.example.belajar_spring.service.KontakTemanService;
import com.example.belajar_spring.service.KerjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/kontak")
public class KontakController {

    @Autowired
    private KeluargaService keluargaService;

    @Autowired
    private KontakTemanService kontakTemanService;

    @Autowired
    private KerjaService kerjaService;

    @GetMapping
    public String tampilkanKontakUmum(
            @RequestParam(required = false) String sumber,
            @RequestParam(required = false) String keyword,
            Model model) {

        List<Kontak> semuaKontak = new ArrayList<>();

        List<Kontak> dariKeluarga = keluargaService.getAll();
        dariKeluarga.forEach(k -> k.setSumber("Keluarga"));

        List<Kontak> dariTeman = kontakTemanService.getAll();
        dariTeman.forEach(k -> k.setSumber("Teman"));

        List<Kontak> dariKerja = kerjaService.getAll();
        dariKerja.forEach(k -> k.setSumber("Kerja"));

        semuaKontak.addAll(dariKeluarga);
        semuaKontak.addAll(dariTeman);
        semuaKontak.addAll(dariKerja);

        // Filter berdasarkan sumber jika ada
        if (sumber != null && !sumber.isBlank()) {
            semuaKontak = semuaKontak.stream()
                    .filter(k -> k.getSumber().equalsIgnoreCase(sumber))
                    .collect(Collectors.toList());
        }

        // Filter berdasarkan keyword jika ada
        if (keyword != null && !keyword.isBlank()) {
            String kwLower = keyword.toLowerCase();
            semuaKontak = semuaKontak.stream()
                    .filter(k -> k.getNama().toLowerCase().contains(kwLower) ||
                            (k.getNoTelp() != null && k.getNoTelp().contains(keyword)) ||
                            (k.getEmail() != null && k.getEmail().toLowerCase().contains(kwLower)))
                    .collect(Collectors.toList());
        }

        // Masukkan ke model
        model.addAttribute("total", dariKeluarga.size() + dariTeman.size() + dariKerja.size());
        model.addAttribute("keluargaCount", dariKeluarga.size());
        model.addAttribute("temanCount", dariTeman.size());
        model.addAttribute("kerjaCount", dariKerja.size());
        model.addAttribute("kontakList", semuaKontak);
        model.addAttribute("keyword", keyword != null ? keyword : "");
        model.addAttribute("sumber", sumber != null ? sumber : "");

        return "hasilpencarian";
    }
}
```
