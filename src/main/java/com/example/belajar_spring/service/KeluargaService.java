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
