public class WurfMitLuftwiderstand extends Wurf {

    protected double x0; // Start width

    protected static final double PAIR = 1.293; // Density of air (kg/m³)

    WurfMitLuftwiderstand(double y0, double v0, double alpha) {
        super(y0, v0, alpha);
    }

    public double[][] berechneWurfparabel(double dt, double vW, double cW, double r, double m) {

        double A = Math.PI * Math.pow(r, 2.0);

        final double K = (this.PAIR * cW * A) / (2.0 * m);

        double[][] result = new double[3][1000];

        double vSX, vSY, aLY, aLX, aX, aY;

        double[] vX = new double[1000], vY = new double[1000];

        vX[0] = this.v0X;
        vY[0] = this.v0Y;

        result[0][0] = 0;
        result[1][0] = this.x0;
        result[2][0] = this.y0;

        for (int i = 0; i < result[0].length && result[2][i] > 0; i++) {

            vSX = vW - vX[i];
            vSY = -vY[i];

            aLX = K * (vSX * vSX);
            aLY = K * (vSY * vSY);

            aX = -aLX;
            aY = (vY[i] < 0 ? -aLY : aLY) - G;

            int j = i + 1 >= result[0].length ? i : i + 1;

            result[0][j] = result[0][i] + dt;
            result[1][j] = (aX / 2) * (dt * dt) + vX[i] * dt + result[1][i];
            result[2][j] = (aY / 2) * (dt * dt) + vY[i] * dt + result[2][i];

            vX[j] = aX * dt + vX[i];
            vY[j] = aY * dt + vY[i];
        }

        return result;
    }

    public double getY0() {
        return y0;
    }

    public double getV0() {
        return v0;
    }

    public double get_alpha() {
        return _alpha;
    }

    public double getyMax() {
        return yMax;
    }

    public double getxMax() {
        return xMax;
    }

    public double gettMax() {
        return tMax;
    }

    public double[] getScheitelpunkt() {
        return scheitelpunkt;
    }
}
