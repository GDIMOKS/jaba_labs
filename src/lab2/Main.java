package lab2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void  main(String[] args) {
        Lab2_1();
    }

    public static void Lab2_1() {
        Task1_1();
        System.out.println("\n////////////////////////////////////\n");
        Task1_2();
        System.out.println("\n////////////////////////////////////\n");
        Task1_3();
    }

    public static void Task1_1() {
        Integer[] arrayMax = {3, 1, 4, 1, 5, 9, 2, 6};
        Double[] arrayMin = {-3d, 1d, 4d, 1d, 5d, 9d, 2d, 6d};

        // �������� ����������� �������� ��� �������� ��������� � ��������
        MonoidClass.IMonoid<Integer> maxMonoid = new MonoidClass.IntegerMaxMonoid();
        MonoidClass.IMonoid<Double> minMonoid = new MonoidClass.DoubleMinMonoid();

        // �������� �������� ��������
        SegmentTree<Integer> maxSegmentTree = new SegmentTree<>(arrayMax, maxMonoid);
        SegmentTree<Double> minSegmentTree = new SegmentTree<>(arrayMin, minMonoid);

        System.out.println("������ �������� ��� ������� � ������� ���������:\n" + maxSegmentTree);
        System.out.println("������ �������� ��� ������� � ������� ��������:\n" + minSegmentTree);

        int left = 0;
        int right = 5;
        // ���������� ������� �� ������ �������� ��� �������� ���������
        int maxResult = maxSegmentTree.query(left, right);
        System.out.println("�������� �� ������� [" + left + ", " + right + "]: " + maxResult);

        // ���������� ������� �� ������ �������� ��� �������� ��������
        double minResult = minSegmentTree.query(left, right);
        System.out.println("������� �� ������� [" + left + ", " + right + "]: " + minResult);

        // �������� ���������� ������� ��� �������� ���������
        MonoidClass.IMonoid<BigInteger> multiplyMonoid = new MonoidClass.BigIntegerMultiplyMonoid();

        // ������� ���������� ����� � �������
        BigInteger base = BigInteger.valueOf(2);
        int exponent = 100;
        BigInteger result = FastBinaryAlgorithmClass.power(multiplyMonoid, base, exponent);

        // ����� ����������
        System.out.println(base + " � ������� " + exponent + " = " + result);
    }

    public static void Task1_2() {

        // ������ �� ���������
        GroupClass.IGroup<Double> additiveGroup = new GroupClass.DoubleAdditiveGroup();
        double value = 5;
        System.out.println("�������� ������� � " + value + " �� ��������: " + additiveGroup.inverse(value));

        // ������ � ����������
        GroupClass.IGroup<Double> multiplicativeGroup = new GroupClass.DoubleMultipyGroup();
        System.out.println("�������� ������� � " + value + " �� ���������: " + multiplicativeGroup.inverse(value));

        Double[] nums = {/*1d, 2d, 3d, 4d, 5d*/3d, 1d, 4d, 1d, 5d, 9d, 2d, 6d};
        GroupClass.IGroup<Double> additiveGroup2 = new GroupClass.DoubleAdditiveGroup();
        PrefixSumClass<Double> ps = new PrefixSumClass(nums, additiveGroup2);

        // ����� ���� �� ��������
        System.out.println("����� ��������� �� ������� [2, 5]: " + ps.rangeSum(2, 5, additiveGroup2));
        System.out.println("����� ��������� �� ������� [0, 2]: " + ps.rangeSum(0, 2, additiveGroup2));
    }

    public static void Task1_3() {
        // ������ ��� ������ �������
        GroupClass.IGroup<Integer> additiveGroup = new GroupClass.IntegerAdditiveGroup();
        MonoidClass.IMonoid<Integer> multiplyMonoid = new MonoidClass.IntegerMultiplyMonoid();
        RingClass.Ring<Integer> integerRing = new RingClass.Ring<>(additiveGroup, multiplyMonoid);

        int a = 5;
        int b = 3;
        System.out.println("������ ������������� ������ ��� ������ ������� a =  " + a + ", b = " + b + ":");
        integerRing.print(a, b);

        // ������ ��� ����������� ����������
        MonoidClass.IMonoid<Boolean> booleanMonoid = new MonoidClass.BooleanMultiplyMonoid();
        GroupClass.IGroup<Boolean> booleanGroup = new GroupClass.BooleanAdditiveGroup();
        RingClass.Ring<Boolean> booleanRing = new RingClass.Ring<>(booleanGroup, booleanMonoid);

        boolean x = true;
        boolean y = false;
        System.out.println("������ ������������� ������ ��� �������� ���������� x = " + x + ", y = " + y + ":");
        booleanRing.print(x, y);

        // �������� �������� �������
        Integer[][] matrixDataInt1 = {
                {1, 2},
                {3, 4}
        };
        MatrixClass<Integer> matrixInt1 = new MatrixClass<>(integerRing, matrixDataInt1);
        // �������� ������� �������
        Boolean[][] matrixDataBool = {{true, false}, {false, true}};
        MatrixClass<Boolean> matrixBool = new MatrixClass<>(booleanRing, matrixDataBool);

        // ����� �������� �������
        System.out.println("�������� �������:\n" + matrixInt1);
        // ����� ������� �������
        System.out.println("������� �������:\n" + matrixBool);

        Integer[][] matrixDataInt2 = {
                {5, 6},
                {7, 8}
        };
        MatrixClass<Integer> matrixInt2 = new MatrixClass<>(integerRing, matrixDataInt2);

        Integer[][] matrixDataInt3 = {
                {9, 10},
                {11, 12}
        };
        MatrixClass<Integer> matrixInt3 = new MatrixClass<>(integerRing, matrixDataInt3);

        // ���������� ������������� ��� ������� ��������
        List<MatrixClass<Integer>> coefficients1 = new ArrayList<>();
        coefficients1.add(matrixInt1);
        coefficients1.add(matrixInt2);
        coefficients1.add(matrixInt3);

        // ���������� ������������� ��� ������� ��������
        List<MatrixClass<Integer>> coefficients2 = new ArrayList<>();
        coefficients2.add(matrixInt3);
        coefficients2.add(matrixInt2);
        coefficients2.add(matrixInt1);

        // �������� ���������
        Polynomial<Integer> poly1 = new Polynomial<>(integerRing, coefficients1);
        Polynomial<Integer> poly2 = new Polynomial<>(integerRing, coefficients2);

        // �������� ��� ����������
        Polynomial<Integer> sum = poly1.add(poly2);
        Polynomial<Integer> difference = poly1.subtract(poly2);
        Polynomial<Integer> product = poly1.multiply(poly2);

        System.out.println("�����:");
        sum.printCoefficients();
        System.out.println();

        System.out.println("��������:");
        difference.printCoefficients();
        System.out.println();

        System.out.println("������������:");
        product.printCoefficients();
        System.out.println();

    }


}

