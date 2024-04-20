package lab2;

import java.math.BigInteger;
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

        // Создание экземпляров моноидов для операций максимума и минимума
        MonoidClass.IMonoid<Integer> maxMonoid = new MonoidClass.IntegerMaxMonoid();
        MonoidClass.IMonoid<Double> minMonoid = new MonoidClass.DoubleMinMonoid();

        // Создание деревьев отрезков
        SegmentTree<Integer> maxSegmentTree = new SegmentTree<>(arrayMax, maxMonoid);
        SegmentTree<Double> minSegmentTree = new SegmentTree<>(arrayMin, minMonoid);

        System.out.println("Дерево отрезков для моноида с взятием максимума:\n" + maxSegmentTree);
        System.out.println("Дерево отрезков для моноида с взятием минимума:\n" + minSegmentTree);

        int left = 0;
        int right = 5;
        // Выполнение запроса на дереве отрезков для операции максимума
        int maxResult = maxSegmentTree.query(left, right);
        System.out.println("Максимум на отрезке [" + left + ", " + right + "]: " + maxResult);

        // Выполнение запроса на дереве отрезков для операции минимума
        double minResult = minSegmentTree.query(left, right);
        System.out.println("Минимум на отрезке [" + left + ", " + right + "]: " + minResult);

        // Создание экземпляра моноида для операции умножения
        MonoidClass.IMonoid<BigInteger> multiplyMonoid = new MonoidClass.BigIntegerMultiplyMonoid();

        // Быстрое возведение числа в степень
        BigInteger base = BigInteger.valueOf(2);
        int exponent = 100;
        BigInteger result = FastBinaryAlgorithmClass.power(multiplyMonoid, base, exponent);

        // Вывод результата
        System.out.println(base + " в степени " + exponent + " = " + result);
    }

    public static void Task1_2() {

        // Группа со сложением
        GroupClass.IGroup<Double> additiveGroup = new GroupClass.DoubleAdditiveGroup();
        double value = 5;
        System.out.println("Обратный элемент к " + value + " по сложению: " + additiveGroup.inverse(value));

        // Группа с умножением
        GroupClass.IGroup<Double> multiplicativeGroup = new GroupClass.DoubleMultipyGroup();
        System.out.println("Обратный элемент к " + value + " по умножению: " + multiplicativeGroup.inverse(value));

        Double[] nums = {/*1d, 2d, 3d, 4d, 5d*/3d, 1d, 4d, 1d, 5d, 9d, 2d, 6d};
        GroupClass.IGroup<Double> additiveGroup2 = new GroupClass.DoubleAdditiveGroup();
        PrefixSumClass<Double> ps = new PrefixSumClass(nums, additiveGroup2);

        // Вывод сумм на отрезках
        System.out.println("Сумма элементов на отрезке [2, 5]: " + ps.rangeSum(2, 5, additiveGroup2));
        System.out.println("Сумма элементов на отрезке [0, 2]: " + ps.rangeSum(0, 2, additiveGroup2));
    }

    public static void Task1_3() {
        // Кольцо над целыми числами
        GroupClass.IGroup<Integer> additiveGroup = new GroupClass.IntegerAdditiveGroup();
        MonoidClass.IMonoid<Integer> multiplyMonoid = new MonoidClass.IntegerMultiplyMonoid();
        RingClass.Ring<Integer> integerRing = new RingClass.Ring<>(additiveGroup, multiplyMonoid);

        int a = 5;
        int b = 3;
        System.out.println("Пример использования кольца над целыми числами a =  " + a + ", b = " + b + ":");
        integerRing.print(a, b);

        // Кольцо над логическими значениями
        MonoidClass.IMonoid<Boolean> booleanMonoid = new MonoidClass.BooleanMultiplyMonoid();
        GroupClass.IGroup<Boolean> booleanGroup = new GroupClass.BooleanAdditiveGroup();
        RingClass.Ring<Boolean> booleanRing = new RingClass.Ring<>(booleanGroup, booleanMonoid);

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
        Polynomial<Integer> integerPolynomial = matrixInt.characteristicPolynomial();

        // Создание полинома над кольцами булевых значений
//        Polynomial<Boolean> boolPolynomial = matrixBool.characteristicPolynomial();

        // Вывод числового полинома
        System.out.println("Числовой полином: " + integerPolynomial);
        // Вывод булевого полинома
//        System.out.println("Булевый полином: " + boolPolynomial);
    }


}

