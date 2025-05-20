# PROJEK_UAS_PBO
Projek CONNEXT untuk Ujian Akhir Semester Pemograman Berorientasi Objek

# Struktur Direktori
Berikut adalah struktur direktori proyek yang akan dibuat:

```plaintext
src/main/java/com/example/belajar_spring
    ├── config
    │   ├── LoginInterceptor.java
    │   └── WebConfig.java
    ├── controller
    │   ├── AuthController.java
    │   ├── KontakController.java
    │   ├── KontakKeluargaController.java
    │   ├── KontakKerjaController.java
    │   ├── KontakTemanController.java
    │   └── SearchController.java
    ├── model
    │   ├── Keluarga.java
    │   ├── Kerja.java
    │   ├── Kontak.java
    │   ├── Teman.java
    │   └── User.java
    ├── repository
    │   └── KontakRepository.java
    ├── service
    │   ├── BelajarController.java
    │   ├── KeluargaService.java
    │   ├── KerjaService.java
    │   ├── KontakService.java
    │   ├── KontakTemanService.java
    │   └── UserService.java
    └── BelajarSpringApplication.java


src/main/resources/
    ├── static/
    ├── templates/
    │   ├── Auth/
    │   |   ├── login.html
    │   |   └──register.html
    │   ├── fragments/
    │   │   ├── footer.html
    │   │   ├── header.html
    │   │   └── layout.html
    │   ├── kontak/
    │   │   ├── add.html
    │   │   ├── index.html
    │   ├── kontak_keluarga/
    │   │   ├── add.html
    │   │   └── index.html
    │   ├── kontak_kerja/
    │   │   ├── add.html
    │   │   └── index.html
    │   └── kontak_teman/
    │   |   ├── add.html
    │   │   └── index.html
    │   ├── dashboard.html
    │   └── welcome.html
    └── application.properties
```

#Config

