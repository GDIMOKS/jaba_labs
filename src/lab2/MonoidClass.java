package lab2;

import java.math.BigInteger;

public class MonoidClass {

    public interface Monoid<T> {
        T identity(); // Нейтральный элемент
        T combine(T a, T b); // Бинарная операция
    }

    public static class IntegerMaxMonoid implements Monoid<Integer> {
        @Override
        public Integer identity() {
            return 0; // Нейтральный элемент для максимума
        }

        @Override
        public Integer combine(Integer a, Integer b) {
            return Math.max(a, b); // Операция максимума
        }
    }

    public static class DoubleMinMonoid implements Monoid<Double> {
        @Override
        public Double identity() {
            return Double.MAX_VALUE; // Нейтральный элемент для минимума
        }

        @Override
        public Double combine(Double a, Double b) {
            return Math.min(a, b); // Операция минимума
        }
    }

    public static class BigIntegerMultiplyMonoid implements Monoid<BigInteger> {
        @Override
        public BigInteger identity() {
            return BigInteger.valueOf(1); // Нейтральный элемент для минимума
        }

        @Override
        public BigInteger combine(BigInteger a, BigInteger b) {
            return a.multiply(b); // Операция минимума
        }
    }

    public static class IntegerMultiplyMonoid implements Monoid<Integer> {
        @Override
        public Integer identity() {
            return 1; // Нейтральный элемент для минимума
        }

        @Override
        public Integer combine(Integer a, Integer b) {
            return a * b; // Операция минимума
        }
    }

    // Класс для моноида по умножению булевых значений
    static class BooleanMultiplyMonoid implements Monoid<Boolean> {
        @Override
        public Boolean combine(Boolean a, Boolean b) {
            return a && b; // Операция умножения - логическое И
        }

        @Override
        public Boolean identity() {
            return true; // Единичный элемент - true
        }
    }
}
