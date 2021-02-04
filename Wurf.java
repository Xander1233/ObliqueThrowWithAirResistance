public class Wurf {

    protected double y0,// Start height & Velocity
        v0, // Velocity
        alpha, // Radians
        _alpha, // Angle
        yMax, // Maximal vertical length
        xMax, // Maximal horizontal length
        tMax, // Maximal time
        v0X, // Horizontal Velocity
        v0Y; // Vertical Velocity

    protected double[] scheitelpunkt = new double[2]; // Vertex

    // Constants
    protected static final double G = 9.81; // Gravity

    Wurf(double y0, double v0, double alpha) {
        this.y0 = y0;
        this.v0 = v0;

        /**
         * Conversion to radians
         */
        this.alpha = alpha * (Math.PI / 180);
        this._alpha = alpha;

        /**
         * Calculate the velocity in:
         *
         * this.v0X - Horizontal
         * this.v0Y - Vertical
         *
         * direction
         */
        this.v0X = this.v0 * Math.cos(this.alpha);
        this.v0Y = this.v0 * Math.sin(this.alpha);
    }

    public double[][] berechneWurfparabel(double dt) {

        /**
         * Calculate the overall time
         */
        this.tMax = (this.v0Y + Math.sqrt(this.v0Y * this.v0Y + 2 * G * this.y0)) / G;

        double _tMax = this.tMax + (dt - (this.tMax % dt));

        double[][] result = new double[3][((int) (_tMax / dt) + 1)];
        double i = 0.0;
        for (int j = 0; j < result[0].length; j++) {
            result[0][j] = i;
            result[1][j] = v0X * i;
            result[2][j] = (-this.G / 2) * (i * i) + this.v0Y * i + y0;

            if (result[2][j] < 0) {
                result[0][j] = this.tMax;
                result[1][j] = v0X * this.tMax;
                result[2][j] = (-this.G / 2) * (this.tMax * this.tMax) + this.v0Y * this.tMax + y0;
            }

            double x = Math.pow(10, 2.0);
            i = (Math.round(i * x) + Math.round(dt * x)) / x;
        }

        this.xMax = result[1][result[1].length - 1];
        this.yMax = result[2][result[2].length - 1];

        return result;
    }

    public void setScheitelpunkt(double x, double y) {
        this.scheitelpunkt[0] = x;
        this.scheitelpunkt[1] = y;
    }

    public double getY0() {
        return this.y0;
    }

    public double getV0() {
        return this.v0;
    }

    public double getAlpha() {
        return this.alpha;
    }

    public double getyMax() {
        return this.yMax;
    }

    public double getxMax() {
        return this.xMax;
    }

    public double gettMax() {
        return this.tMax;
    }

    public double[] getScheitelpunkt() {
        return this.scheitelpunkt;
    }

    public double get_alpha() {
        return _alpha;
    }

    public double getV0X() {
        return v0X;
    }

    public double getV0Y() {
        return v0Y;
    }

    private double max(double[] arr) {
        double v = arr[0];
        for (int i = 0; i < arr.length; i++) if (arr[i] > v) v = arr[i];
        return v;
    }
}
