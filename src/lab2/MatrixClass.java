package lab2;
import lab2.RingClass.*;

import java.util.Arrays;
import java.util.List;

public class MatrixClass<T> {
    protected Ring<T> ring;
    protected T[][] data; // ƒвумерный массив элементов кольца

    //  онструктор класса
    public MatrixClass(Ring<T> ring, T[][] data) {
        this.ring = ring;
        this.data = data;
    }

    //  онструктор класса
    public MatrixClass(MatrixClass<T> matrix) {
        this.ring = matrix.ring;
        this.data = matrix.data;
    }

    public int getRowCount() {
        return data.length;
    }

    public int getColumnCount() {
        return data[0].length;
    }

    public T getElement(int row, int col) {
        return data[row][col];
    }

    // ћетод дл€ умножени€ матрицы на скал€р
    public MatrixClass<T> multiplyByScalar(T scalar) {
        int rows = this.getRowCount();
        int cols = this.getColumnCount();
        T[][] result = (T[][]) new Object[rows][cols];


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = ring.multiply(data[i][j], scalar);
            }
        }

        return new MatrixClass(ring, result);
    }


    // ћетод вычитани€ матрицы из текущей матрицы
    public MatrixClass<T> subtract(MatrixClass<T> other) {
        int rows = this.getRowCount();
        int cols = this.getColumnCount();
        T[][] result = (T[][]) new Object[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = ring.add(this.getElement(i, j), ring.negate(other.getElement(i, j)));
            }
        }

        return new MatrixClass(ring, result);
    }

    // ћетод умножени€ матрицы на другую матрицу
    public MatrixClass<T> multiply(MatrixClass<T> other) {
        int rowsA = this.getRowCount();
        int colsA = this.getColumnCount();
        int colsB = other.getColumnCount();

        T[][] result = (T[][]) new Object[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                T sum = ring.zero();
                for (int k = 0; k < colsA; k++) {
                    sum = ring.add(sum, ring.multiply(this.data[i][k], other.getElement(k, j)));
                }
                result[i][j] = sum;
            }
        }

        return new MatrixClass(ring, result);
    }

    // ћетод дл€ вычислени€ характеристического полинома
    public Polynomial<T> characteristicPolynomial() {
        int n = this.getRowCount(); // ѕолучаем размерность матрицы
        MatrixClass<T> singleMatrix = new MatrixClass(ring, identityMatrix(n)); // —оздаем единичную матрицу той же размерности

        // —оздаем полином, начинаем с единичной матрицы
        Polynomial<T> characteristicPoly = new Polynomial<T>(singleMatrix);

        for (int i = 0; i < n; i++) {
            // ¬ычитаем из матрицы диагональную матрицу, умноженную на собственное значение
            MatrixClass<T> subtracted = this.subtract(this.multiplyByScalar(singleMatrix.data[i][i]));
            // ”множаем текущий характеристический полином на полученную матрицу
            characteristicPoly = new Polynomial(characteristicPoly.multiply(subtracted));
        }
        characteristicPoly.coefficients = (List<T>) Arrays.asList(characteristicPoly.data[0]);
        return characteristicPoly;
    }

    // ћетод дл€ создани€ единичной матрицы
    private Object[][] identityMatrix(int n) {
        Object[][] identity = new Object[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                identity[i][j] = (i == j) ? 1 : 0; // «аполн€ем единицами главную диагональ, остальные нул€ми
            }
        }
        return identity;
    }

    // ћетод дл€ вывода матрицы в виде строки
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T[] row : data) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }

}
