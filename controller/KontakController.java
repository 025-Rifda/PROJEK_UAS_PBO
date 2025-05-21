package com.example.belajar_spring.controller;

import com.example.belajar_spring.service.KeluargaService;
import com.example.belajar_spring.service.KontakTemanService;
import com.example.belajar_spring.service.KerjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public String tampilkanSemuaKontak(
            @RequestParam(value = "sumber", required = false) String sumber,
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        List<Object> kontakList;

        // Filter berdasarkan sumber
        if (sumber != null && !sumber.isEmpty()) {
            switch (sumber.toLowerCase()) {
                case "keluarga":
                    kontakList = (List<Object>) (List<?>) keluargaService.getAll();
                    break;
                case "teman":
                    kontakList = (List<Object>) (List<?>) kontakTemanService.getAll();
                    break;
                case "kerja":
                    kontakList = (List<Object>) (List<?>) kerjaService.getAll();
                    break;
                default:
                    kontakList = new java.util.ArrayList<>();
            }
        } else {
            // Gabungkan semua kontak jika tidak ada filter sumber
            kontakList = new java.util.ArrayList<>();
            kontakList.addAll((List<Object>) (List<?>) keluargaService.getAll());
            kontakList.addAll((List<Object>) (List<?>) kontakTemanService.getAll());
            kontakList.addAll((List<Object>) (List<?>) kerjaService.getAll());
        }

        // Filter berdasarkan keyword jika ada
        if (keyword != null && !keyword.isEmpty()) {
            String lowerKeyword = keyword.toLowerCase();
            kontakList = kontakList.stream()
                    .filter(k -> {
                        try {
                            java.lang.reflect.Method getNama = k.getClass().getMethod("getNama");
                            java.lang.reflect.Method getEmail = k.getClass().getMethod("getEmail");
                            java.lang.reflect.Method getNoTelp = k.getClass().getMethod("getNoTelp");
                            String nama = (String) getNama.invoke(k);
                            String email = (String) getEmail.invoke(k);
                            String noTelp = (String) getNoTelp.invoke(k);
                            return (nama != null && nama.toLowerCase().contains(lowerKeyword)) ||
                                    (email != null && email.toLowerCase().contains(lowerKeyword)) ||
                                    (noTelp != null && noTelp.contains(keyword));
                        } catch (Exception e) {
                            return false;
                        }
                    })
                    .collect(java.util.stream.Collectors.toList());
        }

        // Hitung total dan per kategori
        int total = keluargaService.getAll().size() + kontakTemanService.getAll().size() + kerjaService.getAll().size();
        int keluargaCount = keluargaService.getAll().size();
        int temanCount = kontakTemanService.getAll().size();
        int kerjaCount = kerjaService.getAll().size();

        // Tambahkan atribut ke model
        model.addAttribute("kontakList", kontakList);
        model.addAttribute("total", total);
        model.addAttribute("keluargaCount", keluargaCount);
        model.addAttribute("temanCount", temanCount);
        model.addAttribute("kerjaCount", kerjaCount);
        model.addAttribute("sumber", sumber);
        model.addAttribute("keyword", keyword);

        return "kontak/index";
    }
}
