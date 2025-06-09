package com.example.belajar_spring.model;

import java.time.LocalDate;

public class Kerja extends Kontak {

    private String label;
    private String jabatan;
    private String sumber;

    // Constructor lengkap
    public Kerja(String nama, String noTelp, String email, LocalDate tanggalDitambahkan, String jenisKontak,
            String label, String jabatan) {
        super(nama, noTelp, email, tanggalDitambahkan, jenisKontak);
        this.label = label;
        this.jabatan = jabatan;
    }

    // Default constructor untuk Spring form binding
    public Kerja() {
        super(null, null, null, null, "Kerja");
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

}
