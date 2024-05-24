//package lab2;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import lab2.RingClass.*;
//
//
//public class Polynomial<T> {
//
//    private List<T> coefficients;
//    private Ring<T> ring;
//
//    public Polynomial(Ring<T> ring, List<T> coefficients) {
//        this.ring = ring;
//        this.coefficients = coefficients;
//    }
//
//    public Polynomial<T> add(Polynomial<T> other) {
//        int maxDegree = Math.max(this.coefficients.size(), other.coefficients.size());
//        List<T> resultCoefficients = new ArrayList<>(maxDegree);
//
//        for (int i = 0; i < maxDegree; i++) {
//            T thisCoeff = (i < this.coefficients.size()) ? this.coefficients.get(i) : null;
//            T otherCoeff = (i < other.coefficients.size()) ? other.coefficients.get(i) : null;
//
//            if (thisCoeff == null) {
//                resultCoefficients.add(otherCoeff);
//            } else if (otherCoeff == null) {
//                resultCoefficients.add(thisCoeff);
//            } else {
//                resultCoefficients.add(ring.add(thisCoeff, otherCoeff));
//            }
//        }
//
//        return new Polynomial<>(ring, resultCoefficients);
//    }
//
//    public Polynomial<T> subtract(Polynomial<T> other) {
//        int maxDegree = Math.max(this.coefficients.size(), other.coefficients.size());
//        List<T> resultCoefficients = new ArrayList<>(maxDegree);
//
//        for (int i = 0; i < maxDegree; i++) {
//            T thisCoeff = (i < this.coefficients.size()) ? this.coefficients.get(i) : null;
//            T otherCoeff = (i < other.coefficients.size()) ? other.coefficients.get(i) : null;
//
//            if (thisCoeff == null) {
//                resultCoefficients.add(ring.multiply(otherCoeff, ring.negate(ring.one())));
//            } else if (otherCoeff == null) {
//                resultCoefficients.add(thisCoeff);
//            } else {
//                resultCoefficients.add(ring.add(thisCoeff, ring.negate(otherCoeff)));
//            }
//        }
//
//        return new Polynomial<>(ring, resultCoefficients);
//    }
//
//    public Polynomial<T> multiply(Polynomial<T> other) {
//        int thisDegree = this.coefficients.size();
//        int otherDegree = other.coefficients.size();
//        int resultDegree = thisDegree + otherDegree - 1;
//
//        List<T> resultCoefficients = new ArrayList<>(resultDegree);
//
//        for (int i = 0; i < resultDegree; i++) {
//            resultCoefficients.add(new T(ring, ring.zero()));
//        }
//
//        for (int i = 0; i < thisDegree; i++) {
//            for (int j = 0; j < otherDegree; j++) {
//                T product = ring.multiply(this.coefficients.get(i), other.coefficients.get(j));
//                resultCoefficients.set(i + j, resultCoefficients.get(i + j).add(product));
//            }
//        }
//
//        return new Polynomial<>(ring, resultCoefficients);
//    }
//
//    public void printCoefficients() {
//        System.out.println("Коэффициенты полинома:");
//        for (int i = 0; i < coefficients.size(); i++) {
//            System.out.println("Степень " + i + ":");
//            T[][] data = coefficients.get(i).getData();
//            for (T[] row : data) {
//                for (T elem : row) {
//                    System.out.print(elem + " ");
//                }
//                System.out.println();
//            }
//        }
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//
//        int maxLength = 0;
//
//        for (int i = 0; i < coefficients.size(); i++)
//            for (int j = 0; j < coefficients.get(i).data.length; j++)
//                for (int k = 0; k < coefficients.get(i).data[j].length; k++)
//                    maxLength = Math.max(Arrays.toString(coefficients.get(i).getData()[k]).length(), maxLength);
//
//
//        for (int i = 0; i < coefficients.getFirst().getRowCount(); i++) {
//            for (int j = coefficients.size() -1; j >= 0; j--) {
//                T[][] data = coefficients.get(j).getData();
//                String stringData = Arrays.toString(data[i]);
//
//                sb.append(stringData + " ".repeat(maxLength-stringData.length()));
//
//                if (i >= coefficients.get(j).getRowCount() / 2) {
//                    if (j != 0) {
//                        sb.append((" * x^" + j + " + "));
//                    }
//                } else {
//                    sb.append("         ");
//                }
//
//            }
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
//}
//
