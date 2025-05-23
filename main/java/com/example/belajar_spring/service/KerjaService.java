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
