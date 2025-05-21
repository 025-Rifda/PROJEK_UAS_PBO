package com.example.belajar_spring.controller;

import com.example.belajar_spring.model.Teman;
import com.example.belajar_spring.model.Teman.AkunMedSos;
import com.example.belajar_spring.service.KontakTemanService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kontak-teman")
public class KontakTemanController {

    @Autowired
    private KontakTemanService kontakTemanService;

    @GetMapping
    public String listKontak(
            @RequestParam(name = "sort", required = false, defaultValue = "asc") String sort,
            Model model) {

        List<Teman> kontakList;

        if ("desc".equalsIgnoreCase(sort)) {
            kontakList = kontakTemanService.getAllKontakSortedDesc();
        } else {
            kontakList = kontakTemanService.getAllKontakSortedAsc();
        }

        model.addAttribute("kontakList", kontakList);
        model.addAttribute("sort", sort);
        return "kontak_teman/index";
    }

    @GetMapping("/add")
    public String addKontakForm(Model model) {
        model.addAttribute("kontak", new Teman());
        model.addAttribute("isEdit", false);
        return "kontak_teman/add";
    }

    @PostMapping("/add")
    public String saveKontak(@ModelAttribute Teman kontak) {
        kontakTemanService.saveKontak(kontak);
        return "redirect:/kontak-teman";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Teman kontak = kontakTemanService.getKontakByID(id);
        if (kontak == null) {
            return "redirect:/kontak-teman"; // fix typo: 'Kontak-teman' → 'kontak-teman'
        }
        if (kontak.getAkunMedSos() == null) {
            kontak.setAkunMedSos(new AkunMedSos());
        }
        model.addAttribute("kontak", kontak);
        model.addAttribute("isEdit", true);
        return "kontak_teman/add";
    }

    @PostMapping("/edit/{id}")
    public String editKontak(@PathVariable Long id, @ModelAttribute Teman kontak) {
        kontak.setId(id);
        kontakTemanService.updateKontak(kontak);
        return "redirect:/kontak-teman";
    }

    @GetMapping("/delete/{id}")
    public String deleteKontak(@PathVariable Long id) {
        kontakTemanService.deleteKontak(id);
        return "redirect:/kontak-teman";
    }

    @GetMapping("/search")
    public String searchKontak(@RequestParam("keyword") String keyword, Model model) {
        List<Teman> hasil = kontakTemanService.searchKontak(keyword);
        model.addAttribute("kontakList", hasil); // konsisten dengan HTML
        model.addAttribute("keyword", keyword);
        return "hasilpencarian";
    }

}
