#KontakKeluargaController

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
