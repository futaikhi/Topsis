package topsis;

/**
 * Samsung
 */
public class HandPhone {
    public String namaHp;
    public int baterai;
    public int panelLayar;
    public int design;
    public String gambar;
    public double nilai;

    HandPhone(int a, int b, int c, String d) {
        this.baterai = a;
        this.panelLayar = b;
        this.design = c;
        this.namaHp = d;
    }
}