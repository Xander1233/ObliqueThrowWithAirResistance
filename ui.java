public class ui {

    public static void main(String[] args) {
        WurfMitLuftwiderstand wurf = new WurfMitLuftwiderstand(3, 50, 45);

        printWurfparabelMitLuftwiderstand(wurf, 0.05, 0, 1, 1, 1000);

        double[][] res = wurf.berechneWurfparabel(.05, 0, 1, 1, 1000);

        printGraph(new double[][]{res[1], res[2]}, round(wurf.yMax), 0);
    }

    public static void printWurfparabel(Wurf wurf, double dt) {
        double[][] parabel = wurf.berechneWurfparabel(dt);

        System.out.println("Schiefer Wurf");
        System.out.println("Abwurfhöhe            y0    = " + wurf.getY0() + " m");
        System.out.println("Abwurfgeschwindigkeit v0    = " + wurf.getV0() + " m/s");
        System.out.println("Abwurfwinkel          alpha = " + wurf.get_alpha() + "°");
        System.out.println("Wurfhöhe              yMax  = " + wurf.getyMax() + " m");
        System.out.println("Wurfweite             xMax  = " + wurf.getxMax() + " m");
        System.out.println("Wurfzeit              y0    = " + wurf.gettMax() + " s\n");
        System.out.printf("%10s %10s %10s\n", "t/s", "x/m", "y/m");
        for (int i = 0; i < parabel[0].length; i++) {
            System.out.printf("%10s %10s %10s\n", ((Math.floor(parabel[0][i] * 100)) / 100) + "", ((Math.floor(parabel[1][i] * 100)) / 100) + "", ((Math.floor(parabel[2][i] * 100)) / 100) + "");
        }
    }

    public static void printWurfparabelMitLuftwiderstand(WurfMitLuftwiderstand wurf, double dt, double vW, double cW, double r, double m) {

        double[][] parabel = wurf.berechneWurfparabel(dt, vW, cW, r, m);

        System.out.println("Schiefer Wurf");
        System.out.println("Abwurfhöhe            y0    = " + wurf.getY0() + " m");
        System.out.println("Abwurfgeschwindigkeit v0    = " + wurf.getV0() + " m/s");
        System.out.println("Abwurfwinkel          alpha = " + wurf.get_alpha() + "°");
        System.out.println("Wurfhöhe              yMax  = " + wurf.getyMax() + " m");
        System.out.println("Wurfweite             xMax  = " + wurf.getxMax() + " m");
        System.out.println("Wurfzeit              y0    = " + wurf.gettMax() + " s\n");
        System.out.printf("%5s\t%5s\t%5s\n", "t/s", "x/m", "y/m");
        for (int i = 0; i < parabel[0].length; i++) {
            if (i > 0 && parabel[0][i] == 0) i = parabel[0].length;
            else System.out.printf("%5.2f\t%5.2f\t%5.2f\n", parabel[0][i], parabel[1][i], parabel[2][i]);
        }
    }

    public static void printGraph(double[][] data, long max, long min) {

        if (data.length > 2) throw new IndexOutOfBoundsException("The array can only be 2 fields long!");

        System.out.printf("    0%10d%10d%10d%10d%10d%10d%10d%10d%10d\n", 10, 20, 30, 40, 50, 60, 70, 80, 90, 100);

        for (int i = 0; i < data[0].length; i++) {
            if (i > 0 && data[1][i] <= 0) i = data[0].length;
            else {
                String spaces = "";
                for (int j = 0; j < round(data[1][i]); j++) {
                    spaces += " ";
                }
                System.out.printf("%4s" + spaces + "X - " + round(data[1][i]) + "\n", round(data[0][i]) + "");
            }
        }
    }

    private static long round(double v) {
        double t = v - Math.round(v);

        return t < .5 ? Math.round(v) : Math.round(v) + 1;
    }
}
