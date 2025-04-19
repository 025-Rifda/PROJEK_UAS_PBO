package Kontak;

import java.time.LocalDate;
import Manajemen.Provider;

public class Kerja extends Kontak {

    // Atribut Tambahan
    public String Label;
    public String Jabatan;

    // Setter dan Getter
    public void setLabel(String label) {
        this.Label = label;
    }

    public String getLabel() {
        return this.Label;
    }

    public void setJabatan(String jabatan) {
        this.Jabatan = jabatan;
    }

    public String getJabatan() {
        return this.Jabatan;
    }

    // SubClass Constructor
    public Kerja(String nama, String noTelp, String email, LocalDate tanggalDitambahkan, String jenisKontak,
            String label,
            String jabatan) {
        super(nama, noTelp, email, tanggalDitambahkan, jenisKontak);
        this.Label = label;
        this.Jabatan = jabatan;
    }

    @Override
    public void detailKontak() {
        System.out.println("========= Kerja =========");
        System.out.println("Nama      : " + getNama());
        System.out.println("No. Telp  : " + getNoTelp());
        System.out.println("Provider  : " + Provider.detectProvider(getNoTelp()));
        System.out.println("Email     : " + getEmail());
        System.out.println("Perusahaan: " + getLabel());
        System.out.println("Jabatan   : " + getJabatan());
        System.out.println("Tanggal   : " + getTanggalDitambahkan());
        System.out.println("───────────────────────────────────────");
    }

}