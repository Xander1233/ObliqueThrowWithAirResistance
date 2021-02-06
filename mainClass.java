public class mainClass {

    public static void main(String[] args) {
        ThrowWithAirResistance throwWithAirResistance = new ThrowWithAirResistance(2, 75, 45);

        // printParabel(throwWithAirResistance, 0.05, 0, 1, 1, 1000);

        double[][] res = throwWithAirResistance.calculateParabel(.05, 0, 1, 1, 1000);

        printGraph(new double[][]{res[1], res[2]}, round(throwWithAirResistance.getyMax()));
    }

    public static void printParabel(Throw aThrow, double dt) {
        double[][] parabel = aThrow.calculateParabel(dt);

        System.out.println("Oblique Throw");
        System.out.println("drop height         y0    = " + aThrow.getY0() + " m");
        System.out.println("Discharge speed     v0    = " + aThrow.getV0() + " m/s");
        System.out.println("Drop angle          alpha = " + aThrow.get_alpha() + "°");
        System.out.println("Throw height        yMax  = " + aThrow.getyMax() + " m");
        System.out.println("Throwing distance   xMax  = " + aThrow.getxMax() + " m");
        System.out.println("Throwing time       y0    = " + aThrow.gettMax() + " s\n");
        System.out.printf("%10s %10s %10s\n", "t/s", "x/m", "y/m");
        for (int i = 0; i < parabel[0].length; i++) {
            System.out.printf("%10s %10s %10s\n", ((Math.floor(parabel[0][i] * 100)) / 100) + "", ((Math.floor(parabel[1][i] * 100)) / 100) + "", ((Math.floor(parabel[2][i] * 100)) / 100) + "");
        }
    }

    public static void printParabel(ThrowWithAirResistance aThrow, double dt, double vW, double cW, double r, double m) {

        double[][] parabel = aThrow.calculateParabel(dt, vW, cW, r, m);

        System.out.println("Oblique Throw");
        System.out.println("drop height         y0    = " + aThrow.getY0() + " m");
        System.out.println("Discharge speed     v0    = " + aThrow.getV0() + " m/s");
        System.out.println("Drop angle          alpha = " + aThrow.get_alpha() + "°");
        System.out.println("Throw height        yMax  = " + aThrow.getyMax() + " m");
        System.out.println("Throwing distance   xMax  = " + aThrow.getxMax() + " m");
        System.out.println("Throwing time       y0    = " + aThrow.gettMax() + " s\n");
        System.out.printf("%5s\t%5s\t%5s\n", "t/s", "x/m", "y/m");
        for (int i = 0; i < parabel[0].length; i++) {
            if (i > 0 && parabel[0][i] == 0) i = parabel[0].length;
            else System.out.printf("%5.2f\t%5.2f\t%5.2f\n", parabel[0][i], parabel[1][i], parabel[2][i]);
        }
    }

    public static void printGraph(double[][] data, long max) {

        if (data.length > 2) throw new IndexOutOfBoundsException("The array can only have a length of 2!");

        System.out.print("    0");
        for (long i = 10; i <= max + 10; i += 10)
            System.out.printf("%10d", i);
        System.out.print("\n");

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
        return v - Math.round(v) < .5 ? Math.round(v) : Math.round(v) + 1;
    }
}
