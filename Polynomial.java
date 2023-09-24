
public class Polynomial {

    double [] coefficients;

    public Polynomial() {

    }

    public Polynomial(double [] new_coefficients) {
        coefficients = new_coefficients;
    }

    public Polynomial add(Polynomial p) {
        return new Polynomial(this.coefficients + p.coefficients)
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            result += this.coefficients[i] * (x ** i)
        }
        return result;
    }

    public boolean hasRoot(double num) {
        return this.evaluate(num) == 0
    }
}