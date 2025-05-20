**KontakKerjaController.java**

```java
package com.example.belajar_spring.controller;

import com.example.belajar_spring.model.Kerja;
import com.example.belajar_spring.service.KerjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/kontak-kerja")
public class KontakKerjaController {

    @Autowired
    private KerjaService kerjaService;

    @GetMapping
    public String listKontak(
            @RequestParam(name = "sort", required = false, defaultValue = "asc") String sort, Model model) {
        List<Kerja> kontakList;

        if ("desc".equalsIgnoreCase(sort)) {
            kontakList = kerjaService.getAllKontakSortedDesc();
        } else {
            kontakList = kerjaService.getAllKontakSortedAsc();
        }
        model.addAttribute("kontakList", kontakList);
        model.addAttribute("sort", sort);
        return "kontak_kerja/index";
    }

    @GetMapping("/add")
    public String addKontakForm(Model model) {
        model.addAttribute("kontak", new Kerja());
        model.addAttribute("isEdit", false);
        return "kontak_kerja/add";
    }

    @PostMapping("/add")
    public String saveKontak(@ModelAttribute Kerja kontak) {
        kerjaService.saveKontak(kontak);
        return "redirect:/kontak-kerja";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Kerja kontak = kerjaService.getKontakByID(id);
        if (kontak == null) {
            return "redirect:/kontak-kerja";
        }
        model.addAttribute("kontak", kontak);
        model.addAttribute("isEdit", true);
        return "kontak_kerja/add";
    }

    @PostMapping("/edit/{id}")
    public String editKontak(@PathVariable Long id, @ModelAttribute Kerja kontak) {
        kontak.setId(id);
        kerjaService.updateKontak(kontak);
        return "redirect:/kontak-kerja";
    }

    @GetMapping("/delete/{id}")
    public String deleteKontak(@PathVariable Long id) {
        kerjaService.deleteKontak(id);
        return "redirect:/kontak-kerja";
    }
}

```
**Keluarga.java(Model)**

```java
package com.example.belajar_spring.model;

import java.time.LocalDate;

public class Kerja extends Kontak {

    private String label;
    private String jabatan;
    private String sumber;

    // Constructor lengkap
    public Kerja(String nama, String noTelp, String email, LocalDate tanggalDitambahkan, String jenisKontak,
            String label, String jabatan) {
        super(nama, noTelp, email, tanggalDitambahkan, jenisKontak);
        this.label = label;
        this.jabatan = jabatan;
    }

    // Default constructor untuk Spring form binding
    public Kerja() {
        super(null, null, null, null, "Kerja");
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

}

```
**KerjaService.java**

```java
package com.example.belajar_spring.service;

import com.example.belajar_spring.model.Kerja;
import com.example.belajar_spring.model.Kontak;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KerjaService {
    private final List<Kerja> kontakList = new ArrayList<>();
    private Long idCounter = 1L;

    public KerjaService() {
        // Data dummy contoh
        Kerja k1 = new Kerja("Pak Andi", "081233344455", "andi@ptmajujaya.com",
                LocalDate.now(), "Kerja", "PT Maju Jaya", "Manager");
        k1.setId(idCounter++);
        kontakList.add(k1);

        Kerja k2 = new Kerja("Bu Sari", "081266677788", "sari@cvsukses.com",
                LocalDate.now(), "Kerja", "CV Sukses Selalu", "Staff");
        k2.setId(idCounter++);
        kontakList.add(k2);

        Kerja k3 = new Kerja("Budi", "082233445566", "budi@kantor.com",
                LocalDate.now(), "Kerja", "CV Sukses Selalu", "Staff");
        k3.setId(idCounter++);
        kontakList.add(k3);

        Kerja k4 = new Kerja("Zahra", "082299098745", "Zahraaini@kantor.com",
                LocalDate.now(), "Kerja", "Kantor Pusat", "Manajer");
        k4.setId(idCounter++);
        kontakList.add(k4);

        Kerja k5 = new Kerja("Ziva Rania", "088756453245", "Zivarania@kantor.com",
                LocalDate.now(), "Kerja", "Kantor Pusat", "Designer");
        k5.setId(idCounter++);
        kontakList.add(k5);

        Kerja k6 = new Kerja("Rayan Michael", "088546891234", "Rayannayy@ptmajujaya.com",
                LocalDate.now(), "Kerja", "PT. Maju Jaya", "IT Manager");
        k6.setId(idCounter++);
        kontakList.add(k6);

        Kerja k7 = new Kerja("Erick John", "083345678945", "Erichjohn@ptmajujaya.com",
                LocalDate.now(), "Kerja", "PT. Maju Jaya", "Supervisor");
        k7.setId(idCounter++);
        kontakList.add(k7);

    }

    public List<Kontak> getAll() {
        return new ArrayList<>(kontakList); //
    }

    public List<Kerja> getAllKontakSortedAsc() {
        return kontakList.stream().sorted(Comparator.comparing(Kerja::getNama, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    public List<Kerja> getAllKontakSortedDesc() {
        return kontakList.stream()
                .sorted(Comparator.comparing(Kerja::getNama, String.CASE_INSENSITIVE_ORDER).reversed())
                .collect(Collectors.toList());
    }

    public Kerja saveKontak(Kerja kontak) {
        if (kontak.getId() == null) {
            kontak.setId(idCounter++);
            kontakList.add(kontak);
        }
        return kontak;
    }

    public Kerja getKontakByID(Long id) {
        return kontakList.stream().filter(k -> k.getId().equals(id)).findFirst().orElse(null);
    }

    public void updateKontak(Kerja kontak) {
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



