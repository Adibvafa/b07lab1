import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Driver {
    public static void main(String [] args) throws FileNotFoundException {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));

        double [] c1 = {6, 5};
        int [] e1 = {0, 3};
        Polynomial p1 = new Polynomial(c1, e1);

        double [] c2 = {-2,-9};
        int [] e2 = {1, 4};
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial s = p1.add(p2);

        Polynomial p3 = p2.multiply(p1);
        System.out.println(Arrays.toString(p3.coefficients));
        System.out.println(Arrays.toString(p3.exponents));

        Polynomial p4 = new Polynomial(new File("b07lab1/file.txt"));
        System.out.println(Arrays.toString(p4.coefficients));
        System.out.println(Arrays.toString(p4.exponents));

        p2.saveToFile("new.txt");

        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
    }
}