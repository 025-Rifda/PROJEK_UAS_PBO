package Kontak;

import java.time.LocalDate;
import Manajemen.Provider;

public class Keluarga extends Kontak {

    // Atribut Tambahan
    private String hubungan;

    // Setter dan Getter
    public String getHubungan() {
        return hubungan;
    }

    public void setHubungan(String hubungan) {
        this.hubungan = hubungan;
    }

    // Constructor
    public Keluarga(String nama, String noTelp, String email, LocalDate tanggalDitambahkan, String jenisKontak,
            String hubungan) {
        super(nama, noTelp, email, tanggalDitambahkan, jenisKontak);
        this.hubungan = hubungan;
    }

    @Override
    public void detailKontak() {
        System.out.println("============ Keluarga ===========");
        System.out.println("Nama     : " + getNama());
        System.out.println("No. Telp : " + getNoTelp());
        System.out.println("Provider : " + Provider.detectProvider(getNoTelp()));
        System.out.println("Email    : " + getEmail());
        System.out.println("Hubungan : " + hubungan);
        System.out.println("Tanggal  : " + getTanggalDitambahkan());
    }

}