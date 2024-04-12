package lab2;

import java.util.List;

// Класс для полинома над кольцом
class Polynomial<T> {
    private List<T> coefficients; // Список коэффициентов полинома

    // Конструктор класса
    public Polynomial(List<T> coefficients) {
        this.coefficients = coefficients;
    }

    // Метод для получения коэффициента полинома по степени
    public T getCoefficient(int power) {
        return coefficients.get(power);
    }

    // Метод для установки значения коэффициента полинома по степени
    public void setCoefficient(int power, T value) {
        coefficients.set(power, value);
    }

    // Метод для получения степени полинома
    public int getDegree() {
        return coefficients.size() - 1;
    }
}
