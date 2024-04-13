package lab2;

import lab2.MonoidClass.*;

public class GroupClass {
    // ��������� ������
    public interface IGroup<T> extends IMonoid<T> {
        T inverse(T a);
    }

    // ���������� ������ �� ���������
    public static class DoubleAdditiveGroup implements IGroup<Double> {
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

    // ���������� ������ �� ���������
    public static class IntegerAdditiveGroup implements IGroup<Integer> {
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

    // ���������� ������ � ����������
    public static class DoubleMultipyGroup implements IGroup<Double> {
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

    // ����� ��� ������ �� �������� ������� ��������
    public static class BooleanAdditiveGroup implements IGroup<Boolean> {
        @Override
        public Boolean combine(Boolean a, Boolean b) {
            return a || b; // �������� �������� - ���������� ���
        }

        @Override
        public Boolean identity() {
            return false; // ������� ������� - false
        }

        @Override
        public Boolean inverse(Boolean a) {
            return !a; // �������� ������� - ���������
        }
    }
}
