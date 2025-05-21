package com.example.belajar_spring.service;

import com.example.belajar_spring.model.Kontak;
import com.example.belajar_spring.model.Teman;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KontakTemanService {
    private final List<Teman> kontakList = new ArrayList<>();
    private Long idCounter = 1L;

    public KontakTemanService() {
        Teman t1 = new Teman("Zeze", "082100112233", "Zezeimoets@gmail.com",
                LocalDate.now(), "Teman", "Zee", "Bestiie");
        t1.setId(idCounter++);
        t1.setAkunMedSos(new Teman.AkunMedSos("@zeeze", "@zee.tok"));
        kontakList.add(t1);

        Teman t2 = new Teman("Charlie", "082200223344", "charlie@gmail.com",
                LocalDate.now(), "Teman", "Chall", "SMA");
        t2.setId(idCounter++);
        t2.setAkunMedSos(new Teman.AkunMedSos("@charlie", "@chal_tok"));
        kontakList.add(t2);

        Teman t3 = new Teman("Irene Putri", "082300334455", "PutriIrene@gmail.com",
                LocalDate.now(), "Teman", "Iren", "Tetangga");
        t3.setId(idCounter++);
        t3.setAkunMedSos(new Teman.AkunMedSos("@putriirene", "@putrii.princes.irene"));
        kontakList.add(t3);

        Teman t4 = new Teman("Keisya", "085233779045", "Keisyanarana@gmail.com",
                LocalDate.now(), "Teman", "Keyy", "Bestiie");
        t4.setId(idCounter++);
        t4.setAkunMedSos(new Teman.AkunMedSos("@keisya.n", "@keisya.imuetss"));
        kontakList.add(t4);

        Teman t5 = new Teman("Aidan Ares", "083456782300", "Aidanvaroo@gmail.com",
                LocalDate.now(), "Teman", "Aiden", "SMA");
        t5.setId(idCounter++);
        t5.setAkunMedSos(new Teman.AkunMedSos("@aidan.ares", "@aidennnn890"));
        kontakList.add(t5);

        Teman t6 = new Teman("Beni Adit", "083878811290", "-",
                LocalDate.now(), "Teman", "Beni", "SMP");
        t6.setId(idCounter++);
        t6.setAkunMedSos(new Teman.AkunMedSos("@beni.adit", "@beni123"));
        kontakList.add(t6);

        Teman t7 = new Teman("Riyyan Barra", "089889093322", null,
                LocalDate.now(), "Teman", "Riyn", "Kuliah");
        t7.setId(idCounter++);

        kontakList.add(t7);

    }

    public List<Kontak> getAll() {
        return new ArrayList<>(kontakList); //
    }

    public List<Teman> getAllKontakSortedAsc() {
        return kontakList.stream()
                .sorted(Comparator.comparing(Teman::getNama, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    public List<Teman> getAllKontakSortedDesc() {
        return kontakList.stream()
                .sorted(Comparator.comparing(Teman::getNama, String.CASE_INSENSITIVE_ORDER).reversed())
                .collect(Collectors.toList());
    }

    public Teman saveKontak(Teman kontak) {
        if (kontak.getId() == null) {
            kontak.setId(idCounter++);
            kontakList.add(kontak);
        }
        return kontak;
    }

    public Teman getKontakByID(Long id) {
        return kontakList.stream().filter(k -> k.getId().equals(id)).findFirst().orElse(null);
    }

    public void updateKontak(Teman kontak) {
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

    public List<Teman> searchKontak(String keyword) {
        return kontakList.stream()
                .filter(k -> k.getNama().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
