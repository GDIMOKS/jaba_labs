package lab2;

import java.math.BigInteger;

public class MonoidClass {

    public interface IMonoid<T> {
        T identity(); // Нейтральный элемент
        T combine(T a, T b); // Бинарная операция
    }

    public static class IntegerMaxMonoid implements IMonoid<Integer> {
        @Override
        public Integer identity() {
            return 0; // Нейтральный элемент для максимума
        }

        @Override
        public Integer combine(Integer a, Integer b) {
            return Math.max(a, b); // Операция максимума
        }
    }

    public static class DoubleMinMonoid implements IMonoid<Double> {
        @Override
        public Double identity() {
            return Double.MAX_VALUE; // Нейтральный элемент для минимума
        }

        @Override
        public Double combine(Double a, Double b) {
            return Math.min(a, b); // Операция минимума
        }
    }

    public static class BigIntegerMultiplyMonoid implements IMonoid<BigInteger> {
        @Override
        public BigInteger identity() {
            return BigInteger.valueOf(1); // Нейтральный элемент для умножения
        }

        @Override
        public BigInteger combine(BigInteger a, BigInteger b) {
            return a.multiply(b); // Операция умножения
        }
    }

    public static class IntegerMultiplyMonoid implements IMonoid<Integer> {
        @Override
        public Integer identity() {
            return 1; // Нейтральный элемент для умножения
        }

        @Override
        public Integer combine(Integer a, Integer b) {
            return a * b; // Операция умножения
        }
    }

    // Класс для моноида по умножению булевых значений
    public static class BooleanMultiplyMonoid implements IMonoid<Boolean> {
        @Override
        public Boolean combine(Boolean a, Boolean b) {
            return a && b; // Операция умножения - логическое И
        }

        @Override
        public Boolean identity() {
            return true; // Нейтральный элемент - true
        }
    }
}
