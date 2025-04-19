package Kontak;

import java.time.LocalDate;
import Manajemen.Provider;

public class Teman extends Kontak {

    // Atribut Tambahan
    private String panggilan;
    private String label;

    // Setter dan Getter untuk Panggilan
    public String getPanggilan() {
        return panggilan;
    }

    public void setPanggilan(String panggilan) {
        this.panggilan = panggilan;
    }

    // Setter dan Getter untuk Label
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    // Subclass Constructor
    public Teman(String nama, String noTelp, String email, LocalDate tanggalDitambahkan, String jenisKontak,
            String panggilan, String label) {
        super(nama, noTelp, email, tanggalDitambahkan, jenisKontak);
        this.panggilan = panggilan;
        this.label = label;
    }

    // Inner class AkunMedSos yang diubah menjadi static agar tidak terkait dengan
    // instance luar
    public static class AkunMedSos {

        // Atribut
        private String instagram;
        private String tiktok;

        // Constructor
        public AkunMedSos(String instagram, String tiktok) {
            this.instagram = instagram;
            this.tiktok = tiktok;
        }

        // Getter dan Setter untuk Instagram
        public String getInstagram() {
            return instagram;
        }

        public void setInstagram(String instagram) {
            this.instagram = instagram;
        }

        // Getter dan Setter untuk Tiktok
        public String getTiktok() {
            return tiktok;
        }

        public void setTiktok(String tiktok) {
            this.tiktok = tiktok;
        }
    }

    @Override
    public void detailKontak() {
        System.out.println("========== Teman ==========");
        System.out.println("Nama     : " + getNama());
        System.out.println("No. Telp : " + getNoTelp());
        System.out.println("Provider : " + Provider.detectProvider(getNoTelp()));
        System.out.println("Email    : " + getEmail());
        System.out.println("Panggilan : " + panggilan);
        System.out.println("Label    : " + label);
        System.out.println("Tanggal  : " + getTanggalDitambahkan());
        System.out.println("───────────────────────────────────────────");
    }

}