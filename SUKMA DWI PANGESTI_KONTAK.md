# model Kontak.java

```java
package com.example.belajar_spring.model;

import java.time.LocalDate;

import com.example.belajar_spring.model.Kontak;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public abstract class Kontak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private String noTelp;
    private String email;
    private LocalDate tanggalDitambahkan;
    protected String jenisKontak;
    private String sumber;

    public Kontak() {
        // constructor default wajib untuk JPA
    }

    public Kontak(String nama, String noTelp, String email, LocalDate tanggalDitambahkan, String jenisKontak) {
        this.nama = nama;
        this.noTelp = noTelp;
        this.email = email;
        this.tanggalDitambahkan = tanggalDitambahkan;
        this.jenisKontak = jenisKontak;
    }

    // Getter dan Setter id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter dan Setter lain
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

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

}

** KontakService.java**

