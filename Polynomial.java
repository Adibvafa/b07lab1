
public class Polynomial {

    double [] coefficients;

    public Polynomial() {
        this.coefficients = new double[1];
    }

    public Polynomial(double [] new_coefficients) {

        this.coefficients = new double[new_coefficients.length];

        for (int i = 0; i < new_coefficients.length; i++) {
            this.coefficients[i] = new_coefficients[i];
        }
    }

    public Polynomial add(Polynomial p) {

        int i;
        double [] new_coefficients = new double[Math.max(p.coefficients.length, this.coefficients.length)];

        for (i = 0; i < Math.min(p.coefficients.length, this.coefficients.length); i++) {
            new_coefficients[i] = p.coefficients[i] + this.coefficients[i];
        }

        for (; i < p.coefficients.length; i++) {
            new_coefficients[i] = p.coefficients[i];
        }

        for (; i < this.coefficients.length; i++) {
            new_coefficients[i] = this.coefficients[i];
        }


        return new Polynomial(new_coefficients);
    }

    public double evaluate(double x) {
        double result = 0;

        for (int i = 0; i < this.coefficients.length; i++) {
            result += this.coefficients[i] * (Math.pow(x, i));
        }

        return result;
    }

    public boolean hasRoot(double num) {
        return this.evaluate(num) == 0;
    }
}