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
