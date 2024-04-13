package lab2;
import lab2.RingClass.*;

import java.util.Arrays;

public class MatrixClass<T> {
    private Ring<T> ring;
    private T[][] data; // ��������� ������ ��������� ������

    // ����������� ������
    public MatrixClass(Ring<T> ring, T[][] data) {
        this.ring = ring;
        this.data = data;
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

    // ����� ��� ��������� ������� �� ������
    public void multiplyByScalar(T scalar) {
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                data[i][j] = ring.multiply(data[i][j], scalar);
            }
        }
    }

    // ����� ��� ������ ������� � ���� ������
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T[] row : data) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }

}
