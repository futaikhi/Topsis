package topsis;

/**
 * Samsung
 */
public class Data {
    private String nama;
    private int a;
    private int b;
    private int c;
    private int gambar;
    private double nilai;

    Data(int a, int b, int c, String d, int gambar) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.nama = d;
        this.gambar = gambar;
    }

    /**
     * @param nilai the nilai to set
     */
    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    /**
     * @return the gambar
     */
    public int getGambar() {
        return gambar;
    }

    /**
     * @return the nilai
     */
    public double getNilai() {
        return nilai;
    }

    /**
     * @return the a
     */
    public int getA() {
        return a;
    }

    /**
     * @return the b
     */
    public int getB() {
        return b;
    }

    /**
     * @return the c
     */
    public int getC() {
        return c;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }
}