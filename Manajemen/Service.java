package Manajemen;

import Kontak.Kontak;
import Kontak.Keluarga;
import Kontak.Kerja;
import Kontak.Teman;

public class Service {
    private Node head;

    private static class Node {
        Kontak kontak;
        Node next;

        Node(Kontak kontak) {
            this.kontak = kontak;
            this.next = null;
        }
    }

    // Method untuk menambah kontak dengan objek Kontak langsung
    public void tambahKontak(Kontak kontak) {
        Node nodeBaru = new Node(kontak);

        if (head == null) {
            head = nodeBaru;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = nodeBaru;
        }
        System.out.println("Kontak berhasil ditambahkan.");
    }

    // Method untuk menghapus kontak berdasarkan nama
    public void hapusKontak(String nama) {
        if (head == null) {
            throw new IllegalArgumentException("Daftar kontak kosong.");
        }

        if (head.kontak.getNama().equalsIgnoreCase(nama)) {
            head = head.next;
            System.out.println("Kontak berhasil dihapus.");
            return;
        }

        Node current = head;
        while (current.next != null && !current.next.kontak.getNama().equalsIgnoreCase(nama)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            System.out.println("Kontak berhasil dihapus.");
        } else {
            throw new IllegalArgumentException("Kontak tidak ditemukan.");
        }
    }

    // Method untuk mengedit kontak berdasarkan nama
    public void editKontak(String nama, String namaBaru, String nomorBaru) {
        Node current = head;
        while (current != null) {
            if (current.kontak.getNama().equalsIgnoreCase(nama)) {
                current.kontak.setNama(namaBaru);
                current.kontak.setNoTelp(nomorBaru);
                System.out.println("Kontak berhasil diedit.");
                return;
            }
            current = current.next;
        }
        throw new IllegalArgumentException("Kontak tidak ditemukan.");
    }

    // Method untuk menampilkan semua kontak
    public void tampilkanKontak() {
        if (head == null) {
            System.out.println("Daftar kontak kosong.");
            return;
        }

        Node current = head;
        while (current != null) {
            current.kontak.detailKontak();
            current = current.next;
        }
    }

    // Method untuk mencari kontak berdasarkan nama
    public Kontak cariKontak(String nama) {
        Node current = head;
        while (current != null) {
            if (current.kontak.getNama().equalsIgnoreCase(nama)) {
                return current.kontak;
            }
            current = current.next;
        }
        return null;
    }

    // Method untuk mendapatkan ukuran daftar kontak
    public int ukuran() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Method untuk mengubah daftar LinkedList menjadi array Kontak[] untuk
    // keperluan Sorting dan Pencarian
    public Kontak[] toArray() {
        int size = ukuran();
        Kontak[] arrayKontak = new Kontak[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            arrayKontak[index++] = current.kontak;
            current = current.next;
        }
        return arrayKontak;
    }
}