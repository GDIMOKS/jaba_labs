package lab2;
import lab2.RingClass.*;

import java.util.Arrays;
import java.util.List;

public class MatrixClass<T> {
    protected Ring<T> ring;
    protected T[][] data; // Двумерный массив элементов кольца

    protected enum Operation {
        Subtract,
        Add,
        Multiply,
        ScalarMultiply
    }

    // Конструктор класса
    public MatrixClass(Ring<T> ring, T[][] data) {
        this.ring = ring;
        this.data = data;
    }

    // Конструктор класса
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

    public T[][] getData() {
        return data;
    }

    public T getElement(int row, int col) {
        return data[row][col];
    }

    private MatrixClass<T> matrixOperation(MatrixClass<T> other, Operation operationType, T scalar) {
        int rows = this.getRowCount();
        int cols = this.getColumnCount();
        T[][] result = (T[][]) new Object[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                switch (operationType) {
                    case Add:
                        result[i][j] = ring.add(this.data[i][j], other.data[i][j]);
                        break;
                    case Subtract:
                        result[i][j] = ring.add(this.getElement(i, j), ring.negate(other.getElement(i, j)));
                        break;
                    case Multiply:
                        result[i][j] = ring.zero();
                        for (int k = 0; k < this.getColumnCount(); k++) {
                            result[i][j] = ring.add(result[i][j], ring.multiply(this.data[i][k], other.data[k][j]));
                        }
                        break;
                    case ScalarMultiply:
                        result[i][j] = ring.multiply(this.data[i][j], scalar);
                        break;
                }
            }
        }

        return new MatrixClass<>(ring, result);
    }

    // Метод сложения матриц
    public MatrixClass<T> add(MatrixClass<T> other) {
        return matrixOperation(other, Operation.Add, null);
    }

    // Метод вычитания матрицы из текущей матрицы
    public MatrixClass<T> subtract(MatrixClass<T> other) {
        return matrixOperation(other, Operation.Subtract, null);
    }

    // Метод умножения матрицы на другую матрицу
    public MatrixClass<T> multiply(MatrixClass<T> other) {
        return matrixOperation(other, Operation.Multiply, null);
    }

    // Метод для умножения матрицы на скаляр
    public MatrixClass<T> scalarMultiply(T scalar) {
        return matrixOperation(null, Operation.ScalarMultiply, scalar);
    }

    // Метод для создания единичной матрицы
    public T[][] identityMatrix(int n) {
        T[][] identity = (T[][]) new Object[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                identity[i][j] = (i == j) ? this.ring.one() : this.ring.zero();
            }
        }
        return identity;
    }

    // Метод для вывода матрицы в виде строки
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T[] row : data) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }

}
