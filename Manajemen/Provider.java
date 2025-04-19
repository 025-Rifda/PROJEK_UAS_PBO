package Manajemen;

public enum Provider {

    TELKOMSEL("Telkomsel"),
    INDOSAT("Indosat Ooredoo"),
    XL("XL Axiata"),
    AXIS("Axis"),
    TRI("Tri"),
    SMARTFREN("Smartfren"),
    UNKNOWN("Tidak Diketahui");

    private final String namaLengkap;

    Provider(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    @Override
    public String toString() {
        return namaLengkap;
    }

    // Deteksi provider berdasarkan awalan nomor telepon
    public static Provider detectProvider(String noTelp) {
        if (noTelp == null || noTelp.length() < 4) {
            return UNKNOWN;
        }

        // Hilangkan tanda '+' dan awalan negara jika ada
        noTelp = noTelp.replaceAll("[^0-9]", "");
        if (noTelp.startsWith("62")) {
            noTelp = "0" + noTelp.substring(2);
        }

        String prefix = noTelp.substring(0, 4);

        switch (prefix) {
            // Telkomsel
            case "0811":
            case "0812":
            case "0813":
            case "0821":
            case "0822":
            case "0852":
            case "0853":
                return TELKOMSEL;

            // Indosat
            case "0814":
            case "0815":
            case "0816":
            case "0856":
            case "0857":
            case "0858":
                return INDOSAT;

            // XL Axiata
            case "0817":
            case "0818":
            case "0819":
            case "0859":
            case "0877":
            case "0878":
                return XL;

            // Axis
            case "0831":
            case "0832":
            case "0833":
            case "0838":
                return AXIS;

            // Tri (3)
            case "0895":
            case "0896":
            case "0897":
            case "0898":
            case "0899":
                return TRI;

            // Smartfren
            case "0881":
            case "0882":
            case "0883":
            case "0884":
            case "0885":
            case "0886":
            case "0887":
            case "0888":
            case "0889":
                return SMARTFREN;

            default:
                return UNKNOWN;
        }
    }
}