package lab2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lab2.MonoidClass.*;
import lab2.GroupClass.*;
import lab2.RingClass.*;

public class Main {

    public static void Task1_1() {
        // ����� 1
        Integer[] arrayMax = {3, 1, 4, 1, 5, 9, 2, 6};
        Double[] arrayMin = {-3d, 1d, 4d, 1d, 5d, 9d, 2d, 6d};

        // ������� ���������� �������� ��� �������� ��������� � ��������
        Monoid<Integer> maxMonoid = new IntegerMaxMonoid();
        Monoid<Double> minMonoid = new DoubleMinMonoid();

        // ������� ������� ��������
        SegmentTree<Integer> maxSegmentTree = new SegmentTree<>(arrayMax, maxMonoid);
        SegmentTree<Double> minSegmentTree = new SegmentTree<>(arrayMin, minMonoid);

        int left = 0;
        int right = 5;
        // ��������� ������ �� ������ �������� ��� �������� ���������
        int maxResult = maxSegmentTree.query(left, right);
        System.out.println("�������� �� ������� [" + left + ", " + right + "]: " + maxResult);

        // ��������� ������ �� ������ �������� ��� �������� ��������
        double minResult = minSegmentTree.query(left, right);
        System.out.println("������� �� ������� [" + left + ", " + right + "]: " + minResult);

        // ������� ��������� ������� ��� �������� ���������
        Monoid<BigInteger> multiplyMonoid = new BigIntegerMultiplyMonoid();

        // ������� ���������� ����� � �������
        BigInteger base = BigInteger.valueOf(2);
        int exponent = 100;
        BigInteger result = FastBinaryAlgorithmClass.power(multiplyMonoid, base, exponent);

        // ������� ���������
        System.out.println(base + " � ������� " + exponent + " = " + result);
    }

    public static void Task1_2() {

        // ������ �� ���������
        Group<Double> additiveGroup = new DoubleAdditiveGroup();
        double value = 5;
        System.out.println("�������� ������� � " + value + " �� ��������: " + additiveGroup.inverse(value));

        // ������ � ����������
        Group<Double> multiplicativeGroup = new DoubleMultipyGroup();
        System.out.println("�������� ������� � " + value + " �� ���������: " + multiplicativeGroup.inverse(value));

        Double[] nums = {1d, 2d, 3d, 4d, 5d};
        Group<Double> additiveGroup2 = new DoubleAdditiveGroup();
        PrefixSumClass<Double> ps = new PrefixSumClass(nums, additiveGroup2);

        // ������� �������� �� �������
        System.out.println("����� ��������� �� ������� [2, 4]: " + ps.rangeSum(2, 4, additiveGroup2));
        System.out.println("����� ��������� �� ������� [0, 2]: " + ps.rangeSum(0, 2, additiveGroup2));
    }

    public static void Task1_3() {
        // �������� ������ ��� ������ �������
        Group<Integer> additiveGroup = new IntegerAdditiveGroup();
        Monoid<Integer> multiplyMonoid = new IntegerMultiplyMonoid();
        Ring<Integer> integerRing = new Ring<>(additiveGroup, multiplyMonoid);

        // ������� ������������� ������ ��� ������ �������
        int a = 5;
        int b = 3;
        integerRing.print(a, b);

        // ���������� ������ ��� ����������� ����������
        BooleanRing booleanRing = new BooleanRing();

        // ������� ������������� ������ ��� ����������� ����������
        boolean x = true, y = false;
        booleanRing.print(x, y);

        // �������� �������� �������
        Integer[][] matrixData = {{1, 2}, {3, 4}};
        MatrixClass<Integer> matrix = new MatrixClass<>(matrixData);

        // �������� �������� ��� ���������
        List<MatrixClass<Integer>> coefficients = new ArrayList<>();
        coefficients.add(new MatrixClass<>(new Integer[][]{{1, 0}, {0, 1}})); // �������-����������� 1*x^0
        coefficients.add(new MatrixClass<>(new Integer[][]{{0, 1}, {1, 0}})); // �������-����������� 2*x^1
        coefficients.add(new MatrixClass<>(new Integer[][]{{1, 1}, {1, 1}})); // �������-����������� 3*x^2
        Polynomial<MatrixClass<Integer>> polynomial = new Polynomial<>(coefficients);

        // ������ �����������

        // ����� ������ ��� ������ �������
        System.out.println("������ ��� ������ �������:");
        System.out.println("��������: " + additiveGroup.identity());
        System.out.println("���������: " + multiplyMonoid.identity());
        System.out.println("�������� ������� �� ��������: " + additiveGroup.inverse(5));
        System.out.println("������� �������: " + additiveGroup.identity());
        System.out.println("��������� �������: " + multiplyMonoid.identity());
        
        System.out.println();
        // ����� �������� �������
        System.out.println("�������� �������:");
        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                System.out.print(matrix.getElement(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println();

        // ����� �������� ��� ���������
        System.out.println("������� ��� ���������:");
        for (int i = 0; i < polynomial.getDegree() + 1; i++) {
            System.out.println("����������� ��� ������� " + i + ":");
            MatrixClass<Integer> coefficientMatrix = polynomial.getCoefficient(i);
            for (int row = 0; row < coefficientMatrix.getRowCount(); row++) {
                for (int col = 0; col < coefficientMatrix.getColumnCount(); col++) {
                    System.out.print(coefficientMatrix.getElement(row, col) + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void Lab2_1() {
        Task1_1();

        System.out.println();

        Task1_2();

        System.out.println();

        Task1_3();
    }

    public static void  main(String[] args) {
        Lab2_1();
//        System.out.println("������ ���");
    }
}

