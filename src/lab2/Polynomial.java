package lab2;

import java.util.List;
import lab2.RingClass.*;

// Класс для полинома над кольцом
public class Polynomial<T> {
    private final Ring<T> ring;
    private final List<T> coefficients;

    public Polynomial(Ring<T> ring, List<T> coefficients) {
        this.ring = ring;
        this.coefficients = coefficients;
    }

    // Метод для получения коэффициента по степени
    public T getCoefficient(int degree) {
        if (degree < coefficients.size()) {
            return coefficients.get(degree);
        } else {
            return ring.zero(); // Нулевой коэффициент
        }
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
