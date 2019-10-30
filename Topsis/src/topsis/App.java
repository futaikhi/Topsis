package topsis;

public class App {
    public static void main(String[] args) throws Exception {
        String[] nama = { "Samsung", "Xiaomi", "Iphone", "Oppo" };
        int[] a = { 3, 5, 2, 3 };
        int[] b = { 5, 2, 4, 1 };
        int[] c = { 3, 2, 5, 2 };
        int[] w = { 2, 4, 5 };
        String[] gambar = { "4", "1", "23", "2", "3" };
        Topsis obj = new Topsis(a, b, c, nama, w, gambar);
        for (int i = 0; i < nama.length; i++) {
            System.out.println("Merk hp : " + obj.cetak()[i].getNama() + ", Nilai : " + obj.cetak()[i].getNilai());
        }
    }
}