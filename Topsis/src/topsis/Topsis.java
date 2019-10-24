package topsis;

import java.util.Arrays;

/**
 * Topsis
 */
public class Topsis {
    HandPhone samsung = new HandPhone(3, 5, 3, "Samsung");
    HandPhone iPhone = new HandPhone(4, 4, 5, "Iphone");
    HandPhone xiaomi = new HandPhone(5, 3, 2, "Xiaomi");
    HandPhone[] data = { samsung, iPhone, xiaomi };
    int[] w = { 3, 2, 1 };

    // double[] findXn() {
    // double[] xn = new double[3];
    // double temp;
    // double hasil = 0;
    // for (int i = 0; i < 3; i++) {
    // for (int j = 0; j < 3; j++) {
    // temp = Math.pow(data[j][i], 2);
    // hasil = hasil + temp;
    // }
    // xn[i] = Math.sqrt(hasil);
    // hasil = 0;
    // }
    // return xn;
    // }
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

    double[] findXn() {
        double[] xn = new double[3];
        for (int i = 0; i < data.length; i++) {
            xn[0] = Math.pow(data[i].baterai, 2) + xn[0];
            xn[1] = Math.pow(data[i].panelLayar, 2) + xn[1];
            xn[2] = Math.pow(data[i].design, 2) + xn[2];
        }
        for (int i = 0; i < 3; i++) {
            xn[i] = Math.sqrt(xn[i]);
        }
        // double lala = Arrays.stream(xn).min().getAsDouble();
        // System.out.println(lala);
        return xn;
    }

    double[][] findRi() {
        double[][] ri = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ri[0][i] = data[i].baterai / findXn()[0];
                ri[1][i] = data[i].panelLayar / findXn()[1];
                ri[2][i] = data[i].design / findXn()[2];
            }
        }
        return ri;
    }

    double[][] findYij() {
        double[][] yij = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                yij[j][i] = w[i] * findRi()[j][i];
            }
        }
        return yij;
    }

    double[] findMin() {
        double[] min = new double[3];
        for (int i = 0; i < 3; i++) {
            min[i] = Arrays.stream(findYij()[i]).min().getAsDouble();
        }
        return min;
    }

    double[] findMax() {
        double[] max = new double[3];
        for (int i = 0; i < 3; i++) {
            max[i] = Arrays.stream(findYij()[i]).max().getAsDouble();
        }
        return max;
    }

    double[] diPlus() {
        double[] di = new double[3];
        double temp = 0;
        double hasil = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp = findMax()[j] - findYij()[j][i];
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
        double[] di = new double[3];
        double temp = 0;
        double hasil = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp = findMin()[j] - findYij()[j][i];
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
        double[] vi = new double[3];
        for (int i = 0; i < 3; i++) {
            vi[i] = diMin()[i] / (diMin()[i] + diPlus()[i]);
            data[i].nilai = vi[i];
        }
        return vi;
    }

    public HandPhone[] cetak() {
        return data;
    }

    void sort() {
        HandPhone temp;
        for (int i = 0; i < data.length; i++) {
            for (int j = 1; j < data.length - i; j++) {
                if (data[j - 1].nilai < data[j].nilai) {
                    temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    // double[][] findRi() {
    // double[][] ri = new double[3][3];
    // double temp;
    // for (int i = 0; i < 3; i++) {
    // for (int j = 0; j < 3; j++) {
    // temp = data[j][i] / findXn()[i];
    // ri[i][j] = temp;
    // }
    // }
    // return ri;
    // }

}