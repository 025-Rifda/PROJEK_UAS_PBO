import Kontak.*;
import Manajemen.*;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Kontak> daftarKontak = new ArrayList<>();

    public static void main(String[] args) {
        Kontak.salamPembuka();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Convert date string to LocalDate
        LocalDate tanggalFatur = LocalDate.parse("02-01-2024", formatter);
        LocalDate tanggalAni = LocalDate.parse("04-01-2024", formatter);
        LocalDate tanggalZeze = LocalDate.parse("05-02-2024", formatter);
        LocalDate tanggalBudi = LocalDate.parse("02-04-2024", formatter);
        LocalDate tanggalCharlie = LocalDate.parse("30-07-2024", formatter);
        LocalDate tanggalAisyah = LocalDate.parse("30-07-2024", formatter);
        LocalDate tanggalCaca = LocalDate.parse("02-04-2024", formatter);
        LocalDate tanggalBintang = LocalDate.parse("05-02-2024", formatter);
        LocalDate tanggalRasya = LocalDate.parse("30-04-2024", formatter);
        LocalDate tanggalIrene = LocalDate.parse("17-08-2024", formatter);
        LocalDate tanggalAidan = LocalDate.parse("08-06-2024", formatter);
        LocalDate tanggalKeisya = LocalDate.parse("09-09-2024", formatter);
        LocalDate tanggalBeni = LocalDate.parse("12-10-2024", formatter);
        LocalDate tanggalChris = LocalDate.parse("02-02-2025", formatter);
        LocalDate tanggalZahra = LocalDate.parse("28-01-2025", formatter);
        LocalDate tanggalErich = LocalDate.parse("05-07-2024", formatter);
        LocalDate tanggalRayan = LocalDate.parse("04-04-2024", formatter);
        LocalDate tanggalZiva = LocalDate.parse("15-12-2024", formatter);

        // Data Awal
        daftarKontak
                .add(new Keluarga("Fatur", "085976234801", "faturrcatur@mail.com", tanggalFatur, "Keluarga", "Ayah"));
        daftarKontak.add(new Keluarga("Ani", "081234567890", "ani@gmail.com", tanggalAni, "Keluarga", "Ibu"));
        daftarKontak
                .add(new Teman("Zeze", "085623901120", "Zezeimoets@gmail.com", tanggalZeze, "Teman", "zee", "Bestie"));
        daftarKontak
                .add(new Kerja("Budi", "082233445566", "budi@kantor.com", tanggalBudi, "Kerja", "Kantor", "Manager"));
        daftarKontak
                .add(new Teman("Charlie", "083344556677", "charlie@gmail.com", tanggalCharlie, "Teman", "Chal", "SMA"));
        daftarKontak
                .add(new Keluarga("Aisyah", "085356789876", "Aisyhhh@mail.com", tanggalAisyah, "Keluarga", "Kakak"));
        daftarKontak
                .add(new Teman("Irene Putri", "089876092311", "PutriIrene@gmail.com", tanggalIrene, "Teman", "Iren",
                        "SD"));
        daftarKontak
                .add(new Keluarga("Bintang", "083128909822", "Bintangdilangit@mail.com", tanggalBintang, "Keluarga",
                        "Abang"));
        daftarKontak
                .add(new Teman("Keisya", "085233779045", "Keisyanarana@gmail.com", tanggalKeisya, "Teman", "keyy",
                        "Bestie"));
        daftarKontak
                .add(new Kerja("Zahra ", "082299098745", "Zahraaini@kantor.com", tanggalZahra, "Kerja", "Kantor Pusat",
                        "Manager"));
        daftarKontak
                .add(new Teman("Aidan", "083456782300", "Aidanvaroo@gmail.com", tanggalAidan, "Teman", "Aidan", "SMA"));
        daftarKontak
                .add(new Kerja("Ziva", "088756453245", "Zivarania@kantor.com", tanggalZiva, "Kerja", "Kantor",
                        "Karyawan"));
        daftarKontak
                .add(new Kerja("Rayan", "088546891234", "Rayannayy@kantor.com", tanggalRayan, "Kerja", "Kantor",
                        "CEO"));
        daftarKontak
                .add(new Keluarga("Caca", "088267667729", "adikCaca@mail.com", tanggalCaca, "Keluarga", "Adik"));
        daftarKontak
                .add(new Kerja("Erich", "083345678945", "Erichjohn@kantor.com", tanggalErich, "Kerja", "Kantor",
                        "Karyawan"));
        daftarKontak
                .add(new Teman("Beni", "083878811290", "Benabenik@gmail.com", tanggalBeni, "Teman", "Beni", "SMP"));
        daftarKontak
                .add(new Keluarga("Rasya", "088765542900", "Rasyararas@mail.com", tanggalRasya, "Keluarga", "Sepupu"));
        daftarKontak
                .add(new Teman("Chriis", "089889093322", "ChrissZayn@gmail.com", tanggalChris, "Teman", "Zayy",
                        "Kuliah"));

        tampilkanKontakAcak();

        while (true) {
            System.out.println("\n============ MENU ==============");
            System.out.println("1. Tampilkan Kontak");
            System.out.println("2. Cari Kontak");
            System.out.println("3. Tambah Kontak");
            System.out.println("4. Edit Kontak");
            System.out.println("5. Hapus Kontak");
            System.out.println("0. Keluar");
            System.out.println("───────────────────────────────────");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // flush newline

            switch (pilihan) {
                case 1 -> tampilkanKontakAbjad();
                case 2 -> cariKontak();
                case 3 -> tambahKontak();
                case 4 -> editKontak();
                case 5 -> hapusKontak();
                case 0 -> {
                    System.out.println("Terima kasih telah menggunakan CONNEXT.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void tampilkanKontakAcak() {
        System.out.println("----------------- Daftar Kontak --------------");

        List<String> temanList = new ArrayList<>();
        List<String> kerjaList = new ArrayList<>();
        List<String> keluargaList = new ArrayList<>();

        for (Kontak item : daftarKontak) {
            switch (item.getJenisKontak().toLowerCase()) {
                case "teman" -> temanList.add(item.getNama());
                case "kerja" -> kerjaList.add(item.getNama());
                case "keluarga" -> keluargaList.add(item.getNama());
            }
        }

        int max = Math.max(temanList.size(), Math.max(kerjaList.size(), keluargaList.size()));
        System.out.println("┌──────────────┬──────────────┬──────────────┐");
        System.out.printf("│ %-12s │ %-12s │ %-12s │\n", "Teman", "Kerja", "Keluarga");
        System.out.println("├──────────────┼──────────────┼──────────────┤");

        for (int i = 0; i < max; i++) {
            String teman = i < temanList.size() ? temanList.get(i) : "";
            String kerja = i < kerjaList.size() ? kerjaList.get(i) : "";
            String keluarga = i < keluargaList.size() ? keluargaList.get(i) : "";

            System.out.printf("│ %-12s │ %-12s │ %-12s │\n", teman, kerja, keluarga);
        }
        System.out.println("└──────────────┴──────────────┴──────────────┘");
    }

    private static void tampilkanKontakAbjad() {
        while (true) {
            System.out.println("\n====================================");
            System.out.println("          PILIH JENIS KONTAK      ");
            System.out.println("======================================");
            System.out.println("1. Teman");
            System.out.println("2. Kerja");
            System.out.println("3. Keluarga");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.println("──────────────────────────────────────────");
            System.out.print("Pilihan Anda: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            String jenis;

            switch (pilihan) {
                case 1:
                    jenis = "Teman";
                    break;
                case 2:
                    jenis = "Kerja";
                    break;
                case 3:
                    jenis = "Keluarga";
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
                    continue;
            }

            List<Kontak> kontakTertentu = daftarKontak.stream()
                    .filter(k -> k.getJenisKontak().equalsIgnoreCase(jenis))
                    .sorted(Comparator.comparing(Kontak::getNama))
                    .toList();

            if (kontakTertentu.isEmpty()) {
                System.out.println("\n[!] Tidak ada kontak dalam kategori " + jenis + ".");
                continue;
            }

            // Header Tabel
            System.out.println(
                    "\n┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.printf("│ %-118s │\n",
                    "                                                 DAFTAR KONTAK " + jenis.toUpperCase());
            System.out.print(
                    "├────────────────────┬───────────────┬────────────────────┬───────────────────────────────┬");

            switch (jenis) {
                case "Teman" -> {
                    System.out.println("──────────────┬───────────────┤");
                    System.out.printf(
                            "│ %-18s │ %-13s │ %-18s │ %-29s │ %-12s │ %-13s │\n",
                            "Nama", "No. Telp", "Provider", "Email", "Username", "Label");
                    System.out.println(
                            "├────────────────────┼───────────────┼────────────────────┼───────────────────────────────┼──────────────┼───────────────┤");
                }
                case "Keluarga" -> {
                    System.out.println("──────────────────────────────┤");
                    System.out.printf(
                            "│ %-18s │ %-13s │ %-18s │ %-29s │ %-28s │\n",
                            "Nama", "No. Telp", "Provider", "Email", "Hubungan");
                    System.out.println(
                            "├────────────────────┼───────────────┼────────────────────┼───────────────────────────────┼──────────────────────────────┤");
                }
                case "Kerja" -> {
                    System.out.println("──────────────┬───────────────┤");
                    System.out.printf(
                            "│ %-18s │ %-13s │ %-18s │ %-29s │ %-12s │ %-13s │\n",
                            "Nama", "No. Telp", "Provider", "Email", "Perusahaan", "Jabatan");
                    System.out.println(
                            "├────────────────────┼───────────────┼────────────────────┼───────────────────────────────┼──────────────┼───────────────┤");
                }
            }

            // Isi Data
            List<Kontak> array = kontakTertentu;
            for (Kontak k : array) {
                String provider = Provider.detectProvider(k.getNoTelp()).toString();

                System.out.printf(
                        "│ %-18s │ %-13s │ %-18s │ %-29s │",
                        k.getNama(), k.getNoTelp(), provider, k.getEmail());

                if (k instanceof Teman t) {
                    System.out.printf(" %-12s │ %-13s │", t.getPanggilan(), t.getLabel());
                } else if (k instanceof Keluarga kel) {
                    System.out.printf(" %-28s │", kel.getHubungan());
                } else if (k instanceof Kerja kerja) {
                    System.out.printf(" %-12s │ %-13s │", kerja.getLabel(), kerja.getJabatan());
                }

                System.out.println();
            }

            // Footer Tabel
            switch (jenis) {
                case "Teman" -> System.out.println(
                        "└────────────────────┴───────────────┴────────────────────┴───────────────────────────────┴──────────────┴───────────────┘");
                case "Keluarga" -> System.out.println(
                        "└────────────────────┴───────────────┴────────────────────┴───────────────────────────────┴──────────────────────────────┘");
                case "Kerja" -> System.out.println(
                        "└────────────────────┴───────────────┴────────────────────┴───────────────────────────────┴──────────────┴───────────────┘");
            }

            System.out.print("\nTekan Enter untuk kembali...");
            scanner.nextLine();
        }
    }

    private static void cariKontak() {
        while (true) {
            System.out.println("\n─────── Pilih Jenis Pencarian ───────");
            System.out.println("1. Berdasarkan Nama");
            System.out.println("2. Berdasarkan Nomor");
            System.out.println("3. Kembali ke Menu Utama");
            System.out.println("──────────────────────────────────────────");
            System.out.print("Pilihan Anda: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            if (pilihan == 3)
                return;

            System.out.print("Masukkan : ");
            String keyword = scanner.nextLine();

            Kontak[] array = daftarKontak.toArray(new Kontak[0]);
            List<Kontak> hasil = new ArrayList<>();

            switch (pilihan) {
                case 1 -> hasil = Pencarian.searchByName(array, keyword);
                case 2 -> hasil = Pencarian.searchByNumber(array, keyword);
                default -> {
                    System.out.println("Pilihan tidak valid.");
                    continue;
                }
            }

            if (hasil.isEmpty()) {
                System.out.println("Kontak tidak ditemukan.");
            } else {
                System.out.println("\n--- Hasil Pencarian ---");
                for (Kontak k : hasil) {
                    k.detailKontak();
                }
            }
        }
    }

    private static void tambahKontak() {
        System.out.println("\n~~~~~~~ Tambah Kontak ~~~~~~");
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("No. Telp: ");
        String no = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Jenis Kontak (Keluarga/Teman/Kerja): ");
        String jenis = scanner.nextLine();

        LocalDate tanggalDitambahkan = LocalDate.now();

        switch (jenis.toLowerCase()) {
            case "keluarga" -> {
                System.out.print("Hubungan: ");
                String hubungan = scanner.nextLine();
                daftarKontak.add(new Keluarga(nama, no, email, tanggalDitambahkan, "Keluarga", hubungan));
            }
            case "teman" -> {
                System.out.print("Username: ");
                String user = scanner.nextLine();
                System.out.print("Label: ");
                String label = scanner.nextLine();
                daftarKontak.add(new Teman(nama, no, email, tanggalDitambahkan, "Teman", user, label));
            }
            case "kerja" -> {
                System.out.print("Perusahaan: ");
                String kantor = scanner.nextLine();
                System.out.print("Jabatan: ");
                String jabatan = scanner.nextLine();
                daftarKontak.add(new Kerja(nama, no, email, tanggalDitambahkan, "Kerja", kantor, jabatan));
            }
            default -> {
                System.out.println("Jenis kontak tidak dikenali.");
                return;
            }
        }

        System.out.println("Kontak berhasil ditambahkan!");
        tampilkanKontakTanggal();
    }

    private static void tampilkanKontakTanggal() {
        Kontak[] array = daftarKontak.toArray(new Kontak[0]);
        Sorting.sortByDateAdded(array);

        System.out.println("\n~~~~~~~ Kontak Berdasarkan Tanggal Ditambahkan ~~~~~");

        int no = 1;
        for (Kontak k : array) {
            System.out.printf("%2d. %s\n", no++, k.getNama());
        }
    }

    private static void editKontak() {
        System.out.print("Masukkan nama kontak yang ingin diedit: ");
        String nama = scanner.nextLine();

        for (Kontak k : daftarKontak) {
            if (k.getNama().equalsIgnoreCase(nama)) {
                System.out.println("───────────────────────────────────");
                ;
                System.out.println("_____ Apa yang ingin Anda edit? ____");
                System.out.println("1. Nama");
                System.out.println("2. Nomor Telepon");
                System.out.println("3. Email");
                System.out.println("───────────────────────────────────");
                System.out.print("Pilih (1/2/3): ");
                String pilihan = scanner.nextLine();

                switch (pilihan) {
                    case "1" -> {
                        System.out.print("Nama baru: ");
                        k.setNama(scanner.nextLine());
                    }
                    case "2" -> {
                        System.out.print("Nomor baru: ");
                        k.setNoTelp(scanner.nextLine());
                    }
                    case "3" -> {
                        System.out.print("Email baru: ");
                        k.setEmail(scanner.nextLine());
                    }
                    default -> {
                        System.out.println("Pilihan tidak valid.");
                        return;
                    }
                }

                System.out.println("Kontak berhasil diedit.");
                return;
            }
        }

        System.out.println("Kontak tidak ditemukan.");
    }

    private static void hapusKontak() {
        System.out.print("Masukkan nama kontak yang ingin dihapus: ");
        String nama = scanner.nextLine();
        Iterator<Kontak> iterator = daftarKontak.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getNama().equalsIgnoreCase(nama)) {
                iterator.remove();
                System.out.println("Kontak berhasil dihapus.");
                return;
            }
        }
        System.out.println("Kontak tidak ditemukan.");
    }
}