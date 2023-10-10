import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.StreamSupport;

public class Polynomial {

    double [] coefficients;
    int [] exponents;



    public Polynomial() {
        this.coefficients = new double[1];
        this.exponents = new int[1];
    }

    public Polynomial(double [] new_coefficients, int [] new_exponents) {

        this.coefficients = new double[new_coefficients.length];
        this.exponents = new int[new_exponents.length];

        for (int i = 0; i < new_coefficients.length; i++) {
            this.coefficients[i] = new_coefficients[i];
        }

        for (int i = 0; i < new_exponents.length; i++) {
            this.exponents[i] = new_exponents[i];
        }
    }

    public Polynomial(File file) throws FileNotFoundException {
        Scanner input = new Scanner((file));
        String line = input.nextLine();

        String temp = "";
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '-')
                temp = temp + '+';
            temp = temp + line.charAt(i);
        }

        String[] polynomials = temp.split("\\+");
        Polynomial p = new Polynomial();

        for (int i = 0; i < polynomials.length; i++) {
            p = p.add(get_polynomial(polynomials[i]));
        }

        this.coefficients = p.coefficients;
        this.exponents = p.exponents;
    }

    public Polynomial get_polynomial(String str) {
        String[] parts = str.split("x");

        int [] exponents = new int[1];
        double [] coefficients = new double[1];

        coefficients[0] = Double.parseDouble(parts[0]);

        if (parts.length > 1) {
            exponents[0] = Integer.parseInt(parts[1]);
        }

        return new Polynomial(coefficients, exponents);
    }

    public Polynomial add(Polynomial p) {

        int max_exp = Math.max(Arrays.stream(this.exponents).max().orElse(0), Arrays.stream(p.exponents).max().orElse(0));
        double [] temp = new double[max_exp + 1];

        for (int i = 0; i < this.coefficients.length; i++) {
            temp[this.exponents[i]] = this.coefficients[i];
        }

        for (int i = 0; i < p.coefficients.length; i++) {
            temp[p.exponents[i]] += p.coefficients[i];
        }

        int nonzero_elements = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != 0) nonzero_elements++;
        }

        double [] new_coefficients = new double[nonzero_elements];
        int [] new_exponents = new int[nonzero_elements];
        int curr_index = 0;

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != 0) {
                new_coefficients[curr_index] = temp[i];
                new_exponents[curr_index] = i;
                curr_index++;
            }
        }

        return new Polynomial(new_coefficients, new_exponents);

    }

    public double evaluate(double x) {
        double result = 0;

        for (int i = 0; i < this.coefficients.length; i++) {
            result += this.coefficients[i] * (Math.pow(x, this.exponents[i]));
        }

        return result;
    }

    public boolean hasRoot(double num) {
        return this.evaluate(num) == 0;
    }

    public Polynomial multiply(Polynomial p) {

        int i, j = 0;
        int max_exp = Arrays.stream(this.exponents).max().orElse(0) * Arrays.stream(p.exponents).max().orElse(0);
        double [] temp = new double[max_exp + 1];

        for (i = 0; i < this.coefficients.length; i++) {
            for (j = 0; j < p.coefficients.length; j++) {

                double curr_coefficient = this.coefficients[i] * p.coefficients[j];
                int curr_exponent = this.exponents[i] + p.exponents[j];
                temp[curr_exponent] += curr_coefficient;
            }
        }

        int nonzero_elements = 0;
        for (i = 0; i < temp.length; i++) {
            if (temp[i] != 0) nonzero_elements++;
        }

        double [] new_coefficients = new double[nonzero_elements];
        int [] new_exponents = new int[nonzero_elements];
        int curr_index = 0;

        for (i = 0; i < temp.length; i++) {
            if (temp[i] != 0) {
                new_coefficients[curr_index] = temp[i];
                new_exponents[curr_index] = i;
                curr_index++;
            }
        }

        return new Polynomial(new_coefficients, new_exponents);
    }

    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < coefficients.length; i++) {

                if (i > 0 && coefficients[i] > 0) writer.write("+");
                if (exponents[i] != 0) writer.write(Double.toString(coefficients[i]) + "x" + exponents[i]);
                else writer.write(Double.toString(coefficients[i]));
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
