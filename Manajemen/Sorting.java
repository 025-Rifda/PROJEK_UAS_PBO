package Manajemen;

import Kontak.Kontak;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {
    public static void sortByName(Kontak[] kontakArray) {
        Arrays.sort(kontakArray, Comparator.comparing(k -> k.nama.toLowerCase()));
    }

    public static void sortByDateAdded(Kontak[] kontakArray) {
        Arrays.sort(kontakArray, Comparator.comparing(k -> k.tanggalDitambahkan));
    }
}