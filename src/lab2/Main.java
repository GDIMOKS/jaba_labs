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
        // Кольцо над целыми числами
        Group<Integer> additiveGroup = new IntegerAdditiveGroup();
        Monoid<Integer> multiplyMonoid = new IntegerMultiplyMonoid();
        Ring<Integer> integerRing = new Ring<>(additiveGroup, multiplyMonoid);

        // Пример использования кольца над целыми числами
        int a = 5;
        int b = 3;
        System.out.println("Пример использования кольца над целыми числами a =  " + a + ", b = " + b + ":");
        integerRing.print(a, b);

        // Кольцо над логическими значениями
        Monoid<Boolean> booleanMonoid = new BooleanMultiplyMonoid();
        Group<Boolean> booleanGroup = new BooleanAdditiveGroup();
        Ring<Boolean> booleanRing = new Ring<>(booleanGroup, booleanMonoid);

        boolean x = true;
        boolean y = false;
        System.out.println("Пример использования кольца над булевыми значениями x = " + x + ", y = " + y + ":");
        booleanRing.print(x, y);

        // Создание числовой матрицы
        Integer[][] matrixDataInt = {{1, 2}, {3, 4}};
        MatrixClass<Integer> matrixInt = new MatrixClass<>(integerRing, matrixDataInt);
        // Создание булевой матрицы
        Boolean[][] matrixDataBool = {{true, false}, {false, true}};
        MatrixClass<Boolean> matrixBool = new MatrixClass<>(booleanRing, matrixDataBool);

        // Вывод числовой матрицы
        System.out.println("Числовая матрица:\n" + matrixInt);
        // Вывод булевой матрицы
        System.out.println("Булевая матрица:\n" + matrixBool);

        // Создание полинома над кольцами целых чисел
        List<Integer> coefficientsInt = Arrays.asList(1, 2, 3); // Коэффициенты: 1 + 2x + 3x^2
        Polynomial<Integer> integerPolynomial = new Polynomial<>(integerRing, coefficientsInt);
        // Создание полинома над кольцами булевых значений
        List<Boolean> coefficientsBool = Arrays.asList(true, false, true); // Коэффициенты: 1 + x^2
        Polynomial<Boolean> boolPolynomial = new Polynomial<>(booleanRing, coefficientsBool);

        // Вывод числового полинома
        System.out.println("Числовой полином: " + integerPolynomial);
        // Вывод булевого полинома
        System.out.println("Булевый полином: " + boolPolynomial);
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

