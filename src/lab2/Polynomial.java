package lab2;

import java.util.ArrayList;
import java.util.Arrays;
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
        System.out.println("������������ ��������:");
        for (int i = 0; i < coefficients.size(); i++) {
            System.out.println("������� " + i + ":");
            T[][] data = coefficients.get(i).getData();
            for (T[] row : data) {
                for (T elem : row) {
                    System.out.print(elem + " ");
                }
                System.out.println();
            }
        }
    }

    @Override
    public String toString() {
//        ArrayList<StringBuilder> coefficientsSb = new ArrayList<StringBuilder>();
        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < coefficients.size(); i++) {
//            MatrixClass<T> matrix = coefficients.get(i);
//            StringBuilder coefficient = new StringBuilder();
//
//            for (int j = 0; j < matrix.getRowCount(); j++) {
//                coefficient.append(Arrays.toString(matrix.data[j]));
//                if (i != 0 && j == matrix.getRowCount() - 1)
//                    coefficient.append("x^" + i);
//                coefficient.append("\n");
//            }
//            coefficientsSb.add(coefficient);
////            System.out.println(coefficient);
//
//
//        }

//        for (int i = coefficients.size()-1; i >= 0; i--) {
//            sb.append(coefficientsSb.get(i));
//            if (i != 0)
//                sb.append(" + ");
//        }

        int maxLength = 0;

        for (int i = 0; i < coefficients.size(); i++)
            for (int j = 0; j < coefficients.get(i).data.length; j++)
                for (int k = 0; k < coefficients.get(i).data[j].length; k++)
                    maxLength = Math.max(Arrays.toString(coefficients.get(i).getData()[k]).length(), maxLength);


        for (int i = 0; i < coefficients.getFirst().getRowCount(); i++) {
            for (int j = coefficients.size() -1; j >= 0; j--) {
                T[][] data = coefficients.get(j).getData();
                String stringData = Arrays.toString(data[i]);

                sb.append(stringData + " ".repeat(maxLength-stringData.length()));

                if (i >= coefficients.get(j).getRowCount() / 2) {
                    if (j != 0) {
                        sb.append((" * x^" + j + " + "));
                    }
                } else {
                    sb.append("         ");
                }

            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

