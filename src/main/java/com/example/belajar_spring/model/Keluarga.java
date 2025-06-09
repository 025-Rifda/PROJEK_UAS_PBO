package com.example.belajar_spring.model;

import java.time.LocalDate;

public class Keluarga extends Kontak {
    private String hubungan;
    private String sumber;

    // Constructor lengkap, termasuk properti superclass
    public Keluarga(String nama, String noTelp, String email, LocalDate tanggalDitambahkan, String jenisKontak,
            String hubungan) {
        super(nama, noTelp, email, tanggalDitambahkan, jenisKontak);
        this.hubungan = hubungan;
    }

    // Default constructor kosong untuk form binding Spring
    public Keluarga() {
        super(null, null, null, null, "Keluarga");
    }

    public String getHubungan() {
        return hubungan;
    }

    public void setHubungan(String hubungan) {
        this.hubungan = hubungan;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

}
