package lab2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import lab2.RingClass.*;


public class Polynomial<T> {

    private List<MatrixClass<T>> coefficients;
    private Ring<T> ring;

    public Polynomial(Ring<T> ring, List<MatrixClass<T>> coefficients) {
        this.ring = ring;
        this.coefficients = coefficients;
    }

    public Polynomial<T> add(Polynomial<T> other) {
        int maxDegree = Math.max(this.coefficients.size(), other.coefficients.size());
        List<MatrixClass<T>> resultCoefficients = new ArrayList<>(maxDegree);

        for (int i = 0; i < maxDegree; i++) {
            MatrixClass<T> thisCoeff = (i < this.coefficients.size()) ? this.coefficients.get(i) : null;
            MatrixClass<T> otherCoeff = (i < other.coefficients.size()) ? other.coefficients.get(i) : null;

            if (thisCoeff == null) {
                resultCoefficients.add(otherCoeff);
            } else if (otherCoeff == null) {
                resultCoefficients.add(thisCoeff);
            } else {
                resultCoefficients.add(thisCoeff.add(otherCoeff));
            }
        }

        return new Polynomial<>(ring, resultCoefficients);
    }

    public Polynomial<T> subtract(Polynomial<T> other) {
        int maxDegree = Math.max(this.coefficients.size(), other.coefficients.size());
        List<MatrixClass<T>> resultCoefficients = new ArrayList<>(maxDegree);

        for (int i = 0; i < maxDegree; i++) {
            MatrixClass<T> thisCoeff = (i < this.coefficients.size()) ? this.coefficients.get(i) : null;
            MatrixClass<T> otherCoeff = (i < other.coefficients.size()) ? other.coefficients.get(i) : null;

            if (thisCoeff == null) {
                resultCoefficients.add(otherCoeff.scalarMultiply(ring.negate(ring.one())));
            } else if (otherCoeff == null) {
                resultCoefficients.add(thisCoeff);
            } else {
                resultCoefficients.add(thisCoeff.subtract(otherCoeff));
            }
        }

        return new Polynomial<>(ring, resultCoefficients);
    }

    public Polynomial<T> multiply(Polynomial<T> other) {
        int thisDegree = this.coefficients.size();
        int otherDegree = other.coefficients.size();
        int resultDegree = thisDegree + otherDegree - 1;

        List<MatrixClass<T>> resultCoefficients = new ArrayList<>(resultDegree);

        for (int i = 0; i < resultDegree; i++) {
            resultCoefficients.add(new MatrixClass<>(ring, ring.zeroMatrix(this.coefficients.getFirst().getRowCount())));
        }

        for (int i = 0; i < thisDegree; i++) {
            for (int j = 0; j < otherDegree; j++) {
                MatrixClass<T> product = this.coefficients.get(i).multiply(other.coefficients.get(j));
                resultCoefficients.set(i + j, resultCoefficients.get(i + j).add(product));
            }
        }

        return new Polynomial<>(ring, resultCoefficients);
    }

    public void printCoefficients() {
        System.out.println("Коэффициенты полинома:");
        for (int i = 0; i < coefficients.size(); i++) {
            System.out.println("Степень " + i + ":");
            T[][] data = coefficients.get(i).getData();
            for (T[] row : data) {
                for (T elem : row) {
                    System.out.print(elem + " ");
                }
                System.out.println();
            }
        }
    }
}

