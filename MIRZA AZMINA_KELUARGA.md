**KontakKeluargaController**

```java
package com.example.belajar_spring.controller;

import com.example.belajar_spring.model.Keluarga;
import com.example.belajar_spring.service.KeluargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/kontak-keluarga")
public class KontakKeluargaController {

    @Autowired
    private KeluargaService keluargaService;

    /**
     * Menampilkan daftar kontak keluarga dengan opsi sorting berdasarkan nama.
     * 
     * @param sort  arah sorting, "asc" atau "desc", default "asc"
     * @param model model untuk view
     * @return nama view
     */
    @GetMapping
    public String listKontak(
            @RequestParam(name = "sort", required = false, defaultValue = "asc") String sort,
            Model model) {

        List<Keluarga> kontakList;

        if ("desc".equalsIgnoreCase(sort)) {
            kontakList = keluargaService.getAllKontakSortedDesc();
        } else {
            kontakList = keluargaService.getAllKontakSortedAsc();
        }

        model.addAttribute("kontakList", kontakList);
        model.addAttribute("sort", sort); // untuk toggle di tombol sort
        return "kontak_keluarga/index";
    }

    @GetMapping("/add")
    public String addKontakForm(Model model) {
        model.addAttribute("kontak", new Keluarga());
        model.addAttribute("isEdit", false);
        return "kontak_keluarga/add";
    }

    @PostMapping("/add")
    public String saveKontak(@ModelAttribute Keluarga kontak) {
        keluargaService.saveKontak(kontak);
        return "redirect:/kontak-keluarga";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Keluarga kontak = keluargaService.getKontakByID(id);
        if (kontak == null) {
            // handle not found, redirect or show error page
            return "redirect:/kontak-keluarga";
        }
        model.addAttribute("kontak", kontak);
        model.addAttribute("isEdit", true);
        return "kontak_keluarga/add";
    }

    @PostMapping("/edit/{id}")
    public String editKontak(@PathVariable Long id, @ModelAttribute Keluarga kontak) {
        kontak.setId(id);
        keluargaService.updateKontak(kontak);
        return "redirect:/kontak-keluarga";
    }

    @GetMapping("/delete/{id}")
    public String deleteKontak(@PathVariable Long id) {
        keluargaService.deleteKontak(id);
        return "redirect:/kontak-keluarga";
    }

}

```

# Model Keluarga.java

```java
package com.example.belajar_spring.model;

import java.time.LocalDate;

public class Keluarga extends Kontak {
    private String hubungan;
    private String sumber;

    // Constructor lengkap, termasuk properti superclass
    public Keluarga(String nama, String noTelp, String email, LocalDate tanggalDitambahkan, String jenisKontak,
            String hubungan) {
        super(nama, noTelp, email, tanggalDitambahkan, jenisKontak);
        this.hubungan = hubungan;
    }

    // Default constructor kosong untuk form binding Spring
    public Keluarga() {
        super(null, null, null, null, "Keluarga");
    }

    public String getHubungan() {
        return hubungan;
    }

    public void setHubungan(String hubungan) {
        this.hubungan = hubungan;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

}

```

**KeluargaService.java**

```java
package com.example.belajar_spring.service;

import com.example.belajar_spring.model.Keluarga;
import com.example.belajar_spring.model.Kontak;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeluargaService {

    private final List<Keluarga> kontakList = new ArrayList<>();
    private Long idCounter = 1L;

    // Constructor untuk dummy data
    public KeluargaService() {
        kontakList.add(new Keluarga("Fatur", "081234567890", "faturrcatur@mail.com",
                LocalDate.now(), "Keluarga", "Ayah"));
        kontakList.get(0).setId(idCounter++);

        kontakList.add(new Keluarga("Rina", "081234567891", "Raniina@gmail.com",
                LocalDate.now(), "Keluarga", "Ibu"));
        kontakList.get(1).setId(idCounter++);

        kontakList.add(new Keluarga("Aira Aisyah", "081234567892", "Aisyhhh@mail.com",
                LocalDate.now(), "Keluarga", "Kakak"));
        kontakList.get(2).setId(idCounter++);

        kontakList.add(new Keluarga("Bintang Arga", "083128909822", "Bintangdilangit@mail.com",
                LocalDate.now(), "Keluarga", "Abang"));
        kontakList.get(3).setId(idCounter++);

        kontakList.add(new Keluarga("Caca", "088267667729", "adikCaca@mail.com",
                LocalDate.now(), "Keluarga", "Adik"));
        kontakList.get(4).setId(idCounter++);

        kontakList.add(new Keluarga("Rasya", "088765542900", "Rasyararas@mail.com",
                LocalDate.now(), "Keluarga", "Sepupu"));
        kontakList.get(5).setId(idCounter++);
    }

    public List<Kontak> getAll() {
        return new ArrayList<>(kontakList);
    }

    // Opsional: untuk sorting A-Z
    public List<Keluarga> getAllKontakSortedAsc() {
        return kontakList.stream()
                .sorted(Comparator.comparing(Keluarga::getNama, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    // Opsional: untuk sorting Z-A
    public List<Keluarga> getAllKontakSortedDesc() {
        return kontakList.stream()
                .sorted(Comparator.comparing(Keluarga::getNama, String.CASE_INSENSITIVE_ORDER).reversed())
                .collect(Collectors.toList());
    }

    public Keluarga saveKontak(Keluarga kontak) {
        if (kontak.getId() == null) {
            kontak.setId(idCounter++);
            kontakList.add(kontak);
        }
        return kontak;
    }

    public Keluarga getKontakByID(Long id) {
        return kontakList.stream()
                .filter(k -> k.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updateKontak(Keluarga kontak) {
        for (int i = 0; i < kontakList.size(); i++) {
            if (kontakList.get(i).getId().equals(kontak.getId())) {
                kontakList.set(i, kontak);
                return;
            }
        }
    }

    public void deleteKontak(Long id) {
        kontakList.removeIf(k -> k.getId().equals(id));
    }
}

```
