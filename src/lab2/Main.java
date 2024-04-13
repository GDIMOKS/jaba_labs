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
        // ������ ��� ������ �������
        Group<Integer> additiveGroup = new IntegerAdditiveGroup();
        Monoid<Integer> multiplyMonoid = new IntegerMultiplyMonoid();
        Ring<Integer> integerRing = new Ring<>(additiveGroup, multiplyMonoid);

        // ������ ������������� ������ ��� ������ �������
        int a = 5;
        int b = 3;
        System.out.println("������ ������������� ������ ��� ������ ������� a =  " + a + ", b = " + b + ":");
        integerRing.print(a, b);

        // ������ ��� ����������� ����������
        Monoid<Boolean> booleanMonoid = new BooleanMultiplyMonoid();
        Group<Boolean> booleanGroup = new BooleanAdditiveGroup();
        Ring<Boolean> booleanRing = new Ring<>(booleanGroup, booleanMonoid);

        boolean x = true;
        boolean y = false;
        System.out.println("������ ������������� ������ ��� �������� ���������� x = " + x + ", y = " + y + ":");
        booleanRing.print(x, y);

        // �������� �������� �������
        Integer[][] matrixDataInt = {{1, 2}, {3, 4}};
        MatrixClass<Integer> matrixInt = new MatrixClass<>(integerRing, matrixDataInt);
        // �������� ������� �������
        Boolean[][] matrixDataBool = {{true, false}, {false, true}};
        MatrixClass<Boolean> matrixBool = new MatrixClass<>(booleanRing, matrixDataBool);

        // ����� �������� �������
        System.out.println("�������� �������:\n" + matrixInt);
        // ����� ������� �������
        System.out.println("������� �������:\n" + matrixBool);

        // �������� �������� ��� �������� ����� �����
        List<Integer> coefficientsInt = Arrays.asList(1, 2, 3); // ������������: 1 + 2x + 3x^2
        Polynomial<Integer> integerPolynomial = new Polynomial<>(integerRing, coefficientsInt);
        // �������� �������� ��� �������� ������� ��������
        List<Boolean> coefficientsBool = Arrays.asList(true, false, true); // ������������: 1 + x^2
        Polynomial<Boolean> boolPolynomial = new Polynomial<>(booleanRing, coefficientsBool);

        // ����� ��������� ��������
        System.out.println("�������� �������: " + integerPolynomial);
        // ����� �������� ��������
        System.out.println("������� �������: " + boolPolynomial);
    }

    public static void Lab2_1() {
        Task1_1();
        System.out.println("\n////////////////////////////////////\n");

        Task1_2();

        System.out.println("\n////////////////////////////////////\n");

        Task1_3();
    }

    public static void  main(String[] args) {
        Lab2_1();

    }
}

