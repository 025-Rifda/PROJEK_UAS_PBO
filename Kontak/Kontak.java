package Kontak;

import java.time.LocalDate;

public abstract class Kontak {
    // Atribut
    public String nama;
    private String noTelp;
    private String email;
    public LocalDate tanggalDitambahkan;
    protected String jenisKontak;

    // constructor
    public Kontak(String nama, String noTelp, String email, LocalDate tanggalDitambahkan, String jenisKontak) {
        this.nama = nama;
        this.noTelp = noTelp;
        this.email = email;
        this.tanggalDitambahkan = tanggalDitambahkan;
        this.jenisKontak = jenisKontak;

    }

    // Setter dan Getter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getTanggalDitambahkan() {
        return tanggalDitambahkan;
    }

    public void setTanggalDitambahkan(LocalDate tanggalDitambahkan) {
        this.tanggalDitambahkan = tanggalDitambahkan;
    }

    public String getJenisKontak() {
        return jenisKontak;
    }

    public void setJenisKontak(String jenisKontak) {
        this.jenisKontak = jenisKontak;
    }

    // Method
    public static void salamPembuka() {
        System.out.println("\n====================================================");
        System.out.println("           SELAMAT DATANG DI CONNEXT ");
        System.out.println("====================================================");
        System.out.println("           Berikut daftar Kontak Anda");
        System.out.println("----------------------------------------------------\n");
    }

    public void detailKontak() {
        System.out.println("\n==========================================");
        System.out.println("               DETAIL NOMOR");
        System.out.println("==========================================");
        System.out.printf("%-15s : %s%n", "Nama", getNama());
        System.out.printf("%-15s : %s%n", "No. Telp", getNoTelp());
        System.out.printf("%-15s : %s%n", "Email", getEmail());
        System.out.printf("%-15s : %s%n", "Tanggal Ditambahkan", getTanggalDitambahkan());

    }

}