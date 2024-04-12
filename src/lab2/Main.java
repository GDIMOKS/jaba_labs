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
        // часть 1
        Integer[] arrayMax = {3, 1, 4, 1, 5, 9, 2, 6};
        Double[] arrayMin = {-3d, 1d, 4d, 1d, 5d, 9d, 2d, 6d};

        // Создаем экземпляры моноидов для операций максимума и минимума
        Monoid<Integer> maxMonoid = new IntegerMaxMonoid();
        Monoid<Double> minMonoid = new DoubleMinMonoid();

        // Создаем деревья отрезков
        SegmentTree<Integer> maxSegmentTree = new SegmentTree<>(arrayMax, maxMonoid);
        SegmentTree<Double> minSegmentTree = new SegmentTree<>(arrayMin, minMonoid);

        int left = 0;
        int right = 5;
        // Выполняем запрос на дереве отрезков для операции максимума
        int maxResult = maxSegmentTree.query(left, right);
        System.out.println("Максимум на отрезке [" + left + ", " + right + "]: " + maxResult);

        // Выполняем запрос на дереве отрезков для операции минимума
        double minResult = minSegmentTree.query(left, right);
        System.out.println("Минимум на отрезке [" + left + ", " + right + "]: " + minResult);

        // Создаем экземпляр моноида для операции умножения
        Monoid<BigInteger> multiplyMonoid = new BigIntegerMultiplyMonoid();

        // Быстрое возведение числа в степень
        BigInteger base = BigInteger.valueOf(2);
        int exponent = 100;
        BigInteger result = FastBinaryAlgorithmClass.power(multiplyMonoid, base, exponent);

        // Выводим результат
        System.out.println(base + " в степени " + exponent + " = " + result);
    }

    public static void Task1_2() {

        // Группа со сложением
        Group<Double> additiveGroup = new DoubleAdditiveGroup();
        double value = 5;
        System.out.println("Обратный элемент к " + value + " по сложению: " + additiveGroup.inverse(value));

        // Группа с умножением
        Group<Double> multiplicativeGroup = new DoubleMultipyGroup();
        System.out.println("Обратный элемент к " + value + " по умножению: " + multiplicativeGroup.inverse(value));

        Double[] nums = {1d, 2d, 3d, 4d, 5d};
        Group<Double> additiveGroup2 = new DoubleAdditiveGroup();
        PrefixSumClass<Double> ps = new PrefixSumClass(nums, additiveGroup2);

        // Примеры запросов на отрезке
        System.out.println("Сумма элементов на отрезке [2, 4]: " + ps.rangeSum(2, 4, additiveGroup2));
        System.out.println("Сумма элементов на отрезке [0, 2]: " + ps.rangeSum(0, 2, additiveGroup2));
    }

    public static void Task1_3() {
        // Создание кольца над целыми числами
        Group<Integer> additiveGroup = new IntegerAdditiveGroup();
        Monoid<Integer> multiplyMonoid = new IntegerMultiplyMonoid();
        Ring<Integer> integerRing = new Ring<>(additiveGroup, multiplyMonoid);

        // Примеры использования кольца над целыми числами
        int a = 5;
        int b = 3;
        integerRing.print(a, b);

        // Реализация кольца над логическими значениями
        BooleanRing booleanRing = new BooleanRing();

        // Примеры использования кольца над логическими значениями
        boolean x = true, y = false;
        booleanRing.print(x, y);

        // Создание числовой матрицы
        Integer[][] matrixData = {{1, 2}, {3, 4}};
        MatrixClass<Integer> matrix = new MatrixClass<>(matrixData);

        // Создание полинома над матрицами
        List<MatrixClass<Integer>> coefficients = new ArrayList<>();
        coefficients.add(new MatrixClass<>(new Integer[][]{{1, 0}, {0, 1}})); // Матрица-коэффициент 1*x^0
        coefficients.add(new MatrixClass<>(new Integer[][]{{0, 1}, {1, 0}})); // Матрица-коэффициент 2*x^1
        coefficients.add(new MatrixClass<>(new Integer[][]{{1, 1}, {1, 1}})); // Матрица-коэффициент 3*x^2
        Polynomial<MatrixClass<Integer>> polynomial = new Polynomial<>(coefficients);

        // Печать результатов

        // Вывод кольца над целыми числами
        System.out.println("Кольцо над целыми числами:");
        System.out.println("Сложение: " + additiveGroup.identity());
        System.out.println("Умножение: " + multiplyMonoid.identity());
        System.out.println("Обратный элемент по сложению: " + additiveGroup.inverse(5));
        System.out.println("Нулевой элемент: " + additiveGroup.identity());
        System.out.println("Единичный элемент: " + multiplyMonoid.identity());
        
        System.out.println();
        // Вывод числовой матрицы
        System.out.println("Числовая матрица:");
        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                System.out.print(matrix.getElement(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println();

        // Вывод полинома над матрицами
        System.out.println("Полином над матрицами:");
        for (int i = 0; i < polynomial.getDegree() + 1; i++) {
            System.out.println("Коэффициент для степени " + i + ":");
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
//        System.out.println("привет мир");
    }
}

