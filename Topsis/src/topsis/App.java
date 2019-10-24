package topsis;

public class App {
    public static void main(String[] args) throws Exception {
        Topsis obj = new Topsis();
        obj.cetak();
        obj.sort();
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println(obj.cetak()[i].namaHp + " Nilai = " + obj.cetak()[i].nilai);
        }
        
    }
}