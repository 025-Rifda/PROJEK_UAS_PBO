package com.example.belajar_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.belajar_spring.model.Kontak;
import com.example.belajar_spring.repository.KontakRepository;

@Controller
public class SearchController {

    @Autowired
    private KontakRepository kontakRepository;

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Kontak> semua = kontakRepository.findAll();
        System.out.println("=== Semua Data Kontak ===");
        for (Kontak k : semua) {
            System.out.println("Nama: " + k.getNama());
        }

        List<Kontak> hasil = kontakRepository.searchKontak(keyword);
        System.out.println("=== Hasil Pencarian ===");
        for (Kontak k : hasil) {
            System.out.println(">> " + k.getNama());
        }

        model.addAttribute("hasil", hasil);
        model.addAttribute("keyword", keyword);
        return "hasilpencarian";

    }

}
