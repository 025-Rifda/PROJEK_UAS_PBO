package com.example.belajar_spring.model;

import java.time.LocalDate;

public class Teman extends Kontak {
    private String panggilan;
    private String label;
    private String sumber;
    private AkunMedSos akunMedSos = new AkunMedSos(); // Default init

    public Teman(String nama, String noTelp, String email, LocalDate tanggalDitambahkan, String jenisKontak,
            String panggilan, String label) {
        super(nama, noTelp, email, tanggalDitambahkan, jenisKontak);
        this.panggilan = panggilan;
        this.label = label;
    }

    public Teman() {
        super(null, null, null, null, "Teman");
    }

    public String getPanggilan() {
        return panggilan;
    }

    public void setPanggilan(String panggilan) {
        this.panggilan = panggilan;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

    public AkunMedSos getAkunMedSos() {
        return akunMedSos;
    }

    public void setAkunMedSos(AkunMedSos akunMedSos) {
        this.akunMedSos = akunMedSos;
    }

    public static class AkunMedSos {
        private String instagram;
        private String tiktok;

        public AkunMedSos() {
        }

        public AkunMedSos(String instagram, String tiktok) {
            this.instagram = instagram;
            this.tiktok = tiktok;
        }

        public String getInstagram() {
            return instagram;
        }

        public void setInstagram(String instagram) {
            this.instagram = instagram;
        }

        public String getTiktok() {
            return tiktok;
        }

        public void setTiktok(String tiktok) {
            this.tiktok = tiktok;
        }
    }
}
