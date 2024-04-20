package lab2;

import java.lang.reflect.Array;
import java.util.List;
import lab2.RingClass.*;

// Класс для полинома над кольцом
public class Polynomial<T> extends MatrixClass {
    public List<T> coefficients;

    // Конструктор, который принимает объект класса Matrix
    public Polynomial(MatrixClass matrix) {
        super(matrix);
    }

    public Polynomial(Ring<T> ring, T[][] data) {
        super(ring, data);
    }



    // Метод для вывода полинома в виде строки
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coefficients.size(); i++) {
            T coefficient = coefficients.get(i);
            if (!ring.zero().equals(coefficient)) {
                if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (!ring.one().equals(coefficient)) {
                    sb.append(coefficient);
                }
                if (i > 0) {
                    if (i == 1)
                        sb.append("x");
                    else
                        sb.append("x^").append(i);
                } else {
                    sb.append("1");
                }
            }
        }
        return sb.toString();
    }
}
