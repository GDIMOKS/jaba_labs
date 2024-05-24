package lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lab2.RingClass.*;


public class Polynomial<T> {

    private List<T> coefficients;
    private Ring<T> ring;

    public Polynomial(Ring<T> ring, List<T> coefficients) {
        this.ring = ring;
        this.coefficients = coefficients;
    }

    public Polynomial<T> add(Polynomial<T> other) {
        int maxDegree = Math.max(this.coefficients.size(), other.coefficients.size());
        List<T> resultCoefficients = new ArrayList<>(maxDegree);

        for (int i = 0; i < maxDegree; i++) {
            T thisCoeff = (i < this.coefficients.size()) ? this.coefficients.get(i) : null;
            T otherCoeff = (i < other.coefficients.size()) ? other.coefficients.get(i) : null;

            if (thisCoeff == null) {
                resultCoefficients.add(otherCoeff);
            } else if (otherCoeff == null) {
                resultCoefficients.add(thisCoeff);
            } else {
                resultCoefficients.add(ring.add(thisCoeff, otherCoeff));
            }
        }

        return new Polynomial<>(ring, resultCoefficients);
    }

    public Polynomial<T> subtract(Polynomial<T> other) {
        int maxDegree = Math.max(this.coefficients.size(), other.coefficients.size());
        List<T> resultCoefficients = new ArrayList<>(maxDegree);

        for (int i = 0; i < maxDegree; i++) {
            T thisCoeff = (i < this.coefficients.size()) ? this.coefficients.get(i) : null;
            T otherCoeff = (i < other.coefficients.size()) ? other.coefficients.get(i) : null;

            if (thisCoeff == null) {
                resultCoefficients.add(ring.multiply(otherCoeff, ring.negate(ring.one())));
            } else if (otherCoeff == null) {
                resultCoefficients.add(thisCoeff);
            } else {
                resultCoefficients.add(ring.add(thisCoeff, ring.negate(otherCoeff)));
            }
        }

        return new Polynomial<>(ring, resultCoefficients);
    }

    public Polynomial<T> multiply(Polynomial<T> other) {
        int thisDegree = this.coefficients.size();
        int otherDegree = other.coefficients.size();
        int resultDegree = thisDegree + otherDegree - 1;

        List<T> resultCoefficients = new ArrayList<>(resultDegree);

        for (int i = 0; i < resultDegree; i++) {
            resultCoefficients.add(ring.zero());
        }

        for (int i = 0; i < thisDegree; i++) {
            for (int j = 0; j < otherDegree; j++) {
                T product = ring.multiply(this.coefficients.get(i), other.coefficients.get(j));
                resultCoefficients.set(i + j, ring.add(resultCoefficients.get(i + j), product));
            }
        }

        return new Polynomial<>(ring, resultCoefficients);
    }

    public void printCoefficients() {
        System.out.println("Коэффициенты полинома:");
        for (int i = 0; i < coefficients.size(); i++) {
            System.out.println("Степень " + i + ":");
            System.out.println(coefficients.get(i));
        }
    }

    @Override
    public String toString() {
        return ring.getPrint(coefficients);
    }
}

