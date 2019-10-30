package topsis;

import java.util.Arrays;

/**
 * Topsis
 */
public class Topsis {
    Data[] data;
    int[] w;

    public Topsis() {
        findXn();
        findRi();
        findYij();
        findMin();
        findMax();
        diPlus();
        diMin();
        findVi();
        sort();
    }

    public Topsis(int[] a, int[] b, int[] c, String[] nama, int[] w, String[] gambar) {
        this.data = new Data[nama.length];
        for (int i = 0; i < nama.length; i++) {
            this.data[i] = new Data(a[i], b[i], c[i], nama[i], gambar[i]);
        }
        this.w = w;
        findXn();
        findRi();
        findYij();
        findMin();
        findMax();
        diPlus();
        diMin();
        findVi();
        sort();
    }

    double[] findXn() {
        double[] xn = new double[3];
        for (int i = 0; i < data.length; i++) {
            xn[0] = Math.pow(data[i].getA(), 2) + xn[0];
            xn[1] = Math.pow(data[i].getB(), 2) + xn[1];
            xn[2] = Math.pow(data[i].getC(), 2) + xn[2];
        }
        for (int i = 0; i < 3; i++) {
            xn[i] = Math.sqrt(xn[i]);
        }
        // double lala = Arrays.stream(xn).min().getAsDouble();
        // System.out.println(lala);
        return xn;
    }

    double[][] findRi() {
        double[][] ri = new double[data.length][3];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < 3; j++) {
                ri[i][0] = data[i].getA() / findXn()[0];
                ri[i][1] = data[i].getB() / findXn()[1];
                ri[i][2] = data[i].getC() / findXn()[2];
            }
        }
        return ri;
    }

    double[][] findYij() {
        double[][] yij = new double[data.length][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < data.length; j++) {
                yij[j][i] = w[i] * findRi()[j][i];
            }
        }
        return yij;
    }

    double[][] transformasiYij() {
        double[][] yij = new double[3][data.length];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < data.length; j++) {
                yij[i][j] = findYij()[j][i];
            }
        }
        return yij;
    }

    double[] findMin() {
        double[] min = new double[3];
        for (int i = 0; i < 3; i++) {
            min[i] = Arrays.stream(transformasiYij()[i]).min().getAsDouble();
        }
        return min;
    }

    double[] findMax() {
        double[] max = new double[3];
        for (int i = 0; i < 3; i++) {
            max[i] = Arrays.stream(transformasiYij()[i]).max().getAsDouble();
        }
        return max;
    }

    double[] diPlus() {
        double[] di = new double[data.length];
        double temp = 0;
        double hasil = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < 3; j++) {
                temp = findMax()[j] - transformasiYij()[j][i];
                temp = Math.pow(temp, 2);
                hasil = hasil + temp;
            }
            di[i] = Math.sqrt(hasil);
            temp = 0;
            hasil = 0;
        }
        return di;
    }

    double[] diMin() {
        double[] di = new double[data.length];
        double temp = 0;
        double hasil = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < 3; j++) {
                temp = findMin()[j] - transformasiYij()[j][i];
                temp = Math.pow(temp, 2);
                hasil = hasil + temp;
            }
            di[i] = Math.sqrt(hasil);
            temp = 0;
            hasil = 0;
        }
        return di;
    }

    double[] findVi() {
        double[] vi = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            vi[i] = diMin()[i] / (diMin()[i] + diPlus()[i]);
            data[i].setNilai(vi[i]);
        }
        return vi;
    }

    public Data[] cetak() {
        return data;
    }

    void sort() {
        Data temp;
        for (int i = 0; i < data.length; i++) {
            for (int j = 1; j < data.length - i; j++) {
                if (data[j - 1].getNilai() < data[j].getNilai()) {
                    temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

}