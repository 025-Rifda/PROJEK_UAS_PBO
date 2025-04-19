package Manajemen;

import Kontak.Kerja;
import Kontak.Kontak;
import Kontak.Teman;

import java.util.ArrayList;
import java.util.List;

public class Pencarian {

    // Cari berdasarkan nama (case-insensitive)
    public static List<Kontak> searchByName(Kontak[] kontakArray, String keyword) {
        List<Kontak> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Kontak k : kontakArray) {
            if (k.getNama().toLowerCase().contains(lowerKeyword)) {
                result.add(k);
            }
        }
        return result;
    }

    // Cari berdasarkan nomor atau jenis kontak
    public static List<Kontak> searchByNumber(Kontak[] kontakArray, String keyword) {
        List<Kontak> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Kontak k : kontakArray) {
            if (k.getNoTelp().toLowerCase().contains(lowerKeyword) ||
                    k.getJenisKontak().toLowerCase().contains(lowerKeyword)) {
                result.add(k);
            }
        }
        return result;
    }

    // Cari berdasarkan label (khusus Teman & Kerja)
    public static List<Kontak> searchByLabel(Kontak[] kontakArray, String keyword) {
        List<Kontak> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Kontak k : kontakArray) {
            if (k instanceof Teman) {
                Teman t = (Teman) k;
                if (t.getLabel() != null && t.getLabel().toLowerCase().contains(lowerKeyword)) {
                    result.add(t);
                }
            } else if (k instanceof Kerja) {
                Kerja j = (Kerja) k;
                if (j.getLabel() != null && j.getLabel().toLowerCase().contains(lowerKeyword)) {
                    result.add(j);
                }
            }
        }

        return result;
    }
}