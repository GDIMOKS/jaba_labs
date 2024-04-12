package lab2;

import lab2.MonoidClass.*;

public class GroupClass {
    // Интерфейс группы
    interface Group<T> extends Monoid<T> {
        T inverse(T a);
    }

    // Реализация группы со сложением
    static class DoubleAdditiveGroup implements Group<Double> {
        @Override
        public Double identity() {
            return 0d;
        }

        @Override
        public Double combine(Double a, Double b) {
            return a + b;
        }

        @Override
        public Double inverse(Double a) {
            return -a;
        }
    }

    // Реализация группы со сложением
    static class IntegerAdditiveGroup implements Group<Integer> {
        @Override
        public Integer identity() {
            return 0;
        }

        @Override
        public Integer combine(Integer a, Integer b) {
            return a + b;
        }

        @Override
        public Integer inverse(Integer a) {
            return -a;
        }
    }

    // Реализация группы с умножением
    static class DoubleMultipyGroup implements Group<Double> {
        @Override
        public Double identity() {
            return 1d;
        }

        @Override
        public Double combine(Double a, Double b) {
            return a * b;
        }

        @Override
        public Double inverse(Double a) {
            return 1d / a;
        }
    }

    // Класс для группы по сложению булевых значений
    static class BooleanAdditiveGroup implements Group<Boolean> {
        @Override
        public Boolean combine(Boolean a, Boolean b) {
            return a || b; // Операция сложения - логическое ИЛИ
        }

        @Override
        public Boolean identity() {
            return false; // Нулевой элемент - false
        }

        @Override
        public Boolean inverse(Boolean a) {
            return !a; // Обратный элемент - отрицание
        }
    }
}
