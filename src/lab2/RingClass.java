package lab2;
import lab2.MonoidClass.*;
import lab2.GroupClass.*;

import java.util.Arrays;
import java.util.List;

public  class RingClass {

    // Класс кольца
    public interface Ring<T> {
        // Метод для сложения элементов кольца
        public T add(T a, T b) ;

        // Метод для умножения элементов кольца
        public T multiply(T a, T b);

        // Метод для нахождения обратного элемента по сложению
        public T negate(T a);

        // Метод для получения нулевого элемента кольца
        public T zero();

        // Метод для получения единичного элемента кольца
        public T one();

        public void print(T a, T b);

        public String getPrint(List<T> a);

    }

    public static class StandardRing<T> implements Ring<T> {
        protected IGroup<T> additiveGroup; // Группа по сложению
        protected IMonoid<T> multiplyMonoid; // Моноид по умножению

        // Конструктор класса
        public StandardRing(IGroup<T> additiveGroup, IMonoid<T> multiplyMonoid) {
            this.additiveGroup = additiveGroup;
            this.multiplyMonoid = multiplyMonoid;
        }

        // Метод для сложения элементов кольца
        @Override
        public T add(T a, T b) {return additiveGroup.combine(a, b); }

        // Метод для умножения элементов кольца
        @Override
        public T multiply(T a, T b) {
            return multiplyMonoid.combine(a, b);
        }

        // Метод для нахождения обратного элемента по сложению
        public T negate(T a) {
            return additiveGroup.inverse(a);
        }

        // Метод для получения нулевого элемента кольца
        public T zero() {
            return additiveGroup.identity();
        }

        // Метод для получения единичного элемента кольца
        public T one() {
            return multiplyMonoid.identity();
        }

        public void print(T a, T b) {
            System.out.println("Сложение: " + this.add(a, b));
            System.out.println("Умножение: " + this.multiply(a, b));
            System.out.println("Обратный элемент по сложению: " + this.negate(a));
            System.out.println("Нулевой элемент: " + this.zero());
            System.out.println("Единичный элемент: " + this.one());
//            System.out.println("Кольцо: {" +
//                    "Аддитивная группа: " + this.additiveGroup +
//                    ", Мультипликативный моноид: " + this.multiplyMonoid +
//                    '}');
            System.out.println();
        }

        @Override
        public String getPrint(List<T> coefficients) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0, j = coefficients.size()-1; i < coefficients.size(); i++, j--) {
                sb.append(coefficients.get(i));

                if (i != coefficients.size() - 1) {
                    sb.append("x");
                    if (j != 1) {
                        sb.append("^").append(j);
                    }
                }
                if (i != coefficients.size() - 1)
                    sb.append(" + ");
            }
            sb.append("\n");

            return sb.toString();
        }
    }

    public static class MatrixRing<T> implements Ring<MatrixClass<T>> {

        private Ring<T> standardRing;
        private int size;

        public MatrixRing(IGroup<T> additiveGroup, IMonoid<T> multiplyMonoid, int size) {
            this(new StandardRing<T>(additiveGroup, multiplyMonoid), size);
        }

        public MatrixRing(Ring<T> ring, int size) {
            this.standardRing = ring;
            this.size = size;
        }

        public Ring<T> getStandardRing() {
            return standardRing;
        }

        // Метод для сложения элементов кольца
        @Override
        public MatrixClass<T> add(MatrixClass<T> a, MatrixClass<T> b) {
            return a.add(b);
        }

        // Метод для умножения элементов кольца
        public MatrixClass<T> multiply(MatrixClass<T> a, MatrixClass<T> b) {
            return a.multiply(b);
        }

        public MatrixClass<T> multiply(T a, MatrixClass<T> b) {
            return b.scalarMultiply(a);
        }

        public MatrixClass<T> multiply(MatrixClass<T> a, T b) {
            return a.scalarMultiply(b);
        }

        // Метод для нахождения обратного элемента по сложению
        public MatrixClass<T> negate(MatrixClass<T> a) {
            T[][] negatedData = (T[][]) new Object[a.getRowCount()][a.getColumnCount()];

            for (int i = 0; i < a.getRowCount(); i++) {
                for (int j = 0; j < a.getColumnCount(); j++) {
                    negatedData[i][j] = standardRing.negate(a.data[i][j]);
                }
            }
            return new MatrixClass<>(this, negatedData);
        }

        // Метод для получения нулевого элемента кольца
        public MatrixClass<T> zero() {
            T[][] identity = (T[][]) new Object[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    identity[i][j] = standardRing.zero();
                }
            }

            return new MatrixClass<T>(this, identity);
        }

        // Метод для получения единичного элемента кольца
        public MatrixClass<T> one() {
            T[][] identity = (T[][]) new Object[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    identity[i][j] = (i == j) ? standardRing.one() : standardRing.zero();
                }
            }

            return new MatrixClass<T>(this, identity);
        }

        @Override
        public void print(MatrixClass<T> a, MatrixClass<T> b) {
            //            System.out.println("Сложение: " + this.add(a, b));
//            System.out.println("Умножение: " + this.multiply(a, b));
//            System.out.println("Обратный элемент по сложению: " + this.negate(a));
//            System.out.println("Нулевой элемент: " + this.zero());
//            System.out.println("Единичный элемент: " + this.one());
////            System.out.println("Кольцо: {" +
////                    "Аддитивная группа: " + this.additiveGroup +
////                    ", Мультипликативный моноид: " + this.multiplyMonoid +
////                    '}');
            System.out.println();
        }

        public String getPrint(List<MatrixClass<T>> coefficients) {
            StringBuilder sb = new StringBuilder();

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


}
