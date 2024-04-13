package lab2;

import java.math.BigInteger;

public class MonoidClass {

    public interface IMonoid<T> {
        T identity(); // ����������� �������
        T combine(T a, T b); // �������� ��������
    }

    // ����� ��� ������� ��� ������������ ����� ��������
    public static class IntegerMaxMonoid implements IMonoid<Integer> {
        @Override
        public Integer identity() {
            return 0; // ����������� ������� ��� ���������
        }

        @Override
        public Integer combine(Integer a, Integer b) {
            return Math.max(a, b); // �������� ���������
        }
    }

    // ����� ��� ������� ��� ����������� ������������ ��������
    public static class DoubleMinMonoid implements IMonoid<Double> {
        @Override
        public Double identity() {
            return Double.MAX_VALUE; // ����������� ������� ��� ��������
        }

        @Override
        public Double combine(Double a, Double b) {
            return Math.min(a, b); // �������� ��������
        }
    }

    // ����� ��� ������� �� ��������� ������� ����� ��������
    public static class BigIntegerMultiplyMonoid implements IMonoid<BigInteger> {
        @Override
        public BigInteger identity() {
            return BigInteger.ONE; // ����������� ������� ��� ���������
        }

        @Override
        public BigInteger combine(BigInteger a, BigInteger b) {
            return a.multiply(b); // �������� ���������
        }
    }

    // ����� ��� ������� �� ��������� ����� ��������
    public static class IntegerMultiplyMonoid implements IMonoid<Integer> {
        @Override
        public Integer identity() {
            return 1; // ����������� ������� ��� ���������
        }

        @Override
        public Integer combine(Integer a, Integer b) {
            return a * b; // �������� ���������
        }
    }

    // ����� ��� ������� �� ��������� ������� ��������
    public static class BooleanMultiplyMonoid implements IMonoid<Boolean> {
        @Override
        public Boolean combine(Boolean a, Boolean b) {
            return a && b; // �������� ��������� - ���������� �
        }

        @Override
        public Boolean identity() {
            return true; // ����������� ������� - true
        }
    }
}
