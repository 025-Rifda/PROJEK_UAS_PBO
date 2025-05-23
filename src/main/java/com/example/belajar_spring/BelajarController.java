package com.example.belajar_spring;

import com.example.belajar_spring.service.KeluargaService;
import com.example.belajar_spring.service.KontakTemanService;
import com.example.belajar_spring.service.KerjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BelajarController {

    private final KeluargaService keluargaService;
    private final KontakTemanService kontakTemanService;
    private final KerjaService kerjaService;

    @Autowired
    public BelajarController(
            KeluargaService keluargaService,
            KontakTemanService kontakTemanService,
            KerjaService kerjaService) {
        this.keluargaService = keluargaService;
        this.kontakTemanService = kontakTemanService;
        this.kerjaService = kerjaService;
    }

    /**
     * Menampilkan dashboard dengan total jumlah kontak keluarga, teman, dan kerja.
     * 
     * @param model model untuk view
     * @return nama view dashboard
     */
    @GetMapping("/")
    public String showDashboard(Model model) {
        model.addAttribute("totalKeluarga", keluargaService.getAllKontakSortedAsc().size());
        model.addAttribute("totalTeman", kontakTemanService.getAllKontakSortedAsc().size());
        model.addAttribute("totalKerja", kerjaService.getAllKontakSortedAsc().size());
        return "dashboard";
    }
}
