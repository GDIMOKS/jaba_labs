import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class lab2 {

    // часть 1
    public interface Monoid<T> {
        T identity(); // Нейтральный элемент
        T combine(T a, T b); // Бинарная операция
    }

    public static class MonoidMax implements Monoid<Integer> {
        @Override
        public Integer identity() {
            return 0; // Нейтральный элемент для максимума
        }

        @Override
        public Integer combine(Integer a, Integer b) {
            return Math.max(a, b); // Операция максимума
        }
    }

    public static class MonoidMin implements Monoid<Float> {
        @Override
        public Float identity() {
            return Float.MAX_VALUE; // Нейтральный элемент для минимума
        }

        @Override
        public Float combine(Float a, Float b) {
            return Math.min(a, b); // Операция минимума
        }
    }

    public static class MonoidMultiply implements Monoid<BigInteger> {
        @Override
        public BigInteger identity() {
            return BigInteger.valueOf(1); // Нейтральный элемент для минимума
        }

        @Override
        public BigInteger combine(BigInteger a, BigInteger b) {
            return a.multiply(b); // Операция минимума
        }
    }

    public static class IntegerMonoidMultiply implements Monoid<Integer> {
        @Override
        public Integer identity() {
            return 1; // Нейтральный элемент для минимума
        }

        @Override
        public Integer combine(Integer a, Integer b) {
            return a * b; // Операция минимума
        }
    }

    public static class SegmentTree<T> {
        private T[] tree;
        private Monoid<T> monoid;
        private int size;

        public SegmentTree(T[] array, Monoid<T> monoid) {
            this.monoid = monoid;
            size = array.length;
            tree = (T[]) new Object[size * 2];
            buildTree(array);
        }

        private void buildTree(T[] array) {
            for (int i = size, j = 0; i < 2 * size; i++, j++) {
                tree[i] = array[j];
            }
            for (int i = size - 1; i > 0; i--) {
                tree[i] = monoid.combine(tree[i * 2], tree[i * 2 + 1]);
            }
        }

        public void update(int index, T value) {
            index += size;
            tree[index] = value;
            while (index > 1) {
                index /= 2;
                tree[index] = monoid.combine(tree[2 * index], tree[2 * index + 1]);
            }
        }

        public T query(int left, int right) {
            left += size;
            right += size;
            T result = monoid.identity();
            while (left <= right) {
                if ((left & 1) == 1) {
                    result = monoid.combine(result, tree[left]);
                    left++;
                }
                if ((right & 1) == 0) {
                    result = monoid.combine(result, tree[right]);
                    right--;
                }
                left /= 2;
                right /= 2;
            }
            return result;
        }
    }

    public static <T> T power(Monoid<T> monoid, T base, int exponent) {
        T result = monoid.identity();
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = monoid.combine(result, base);
            }
            base = monoid.combine(base, base);
            exponent /= 2;
        }
        return result;
    }


    // Класс кольца
    static class Ring<T> {
        private Group<T> additiveGroup; // Группа по сложению
        private Monoid<T> multiplicativeMonoid; // Моноид по умножению

        // Конструктор класса
        public Ring(Group<T> additiveGroup, Monoid<T> multiplicativeMonoid) {
            this.additiveGroup = additiveGroup;
            this.multiplicativeMonoid = multiplicativeMonoid;
        }

        // Метод для сложения элементов кольца
        public T add(T a, T b) {
            return additiveGroup.combine(a, b);
        }

        // Метод для умножения элементов кольца
        public T multiply(T a, T b) {
            return multiplicativeMonoid.combine(a, b);
        }

        // Метод для нахождения обратного элемента по сложению
        public T negate(T a) {
            return additiveGroup.inverse(a);
        }

        // Метод для получения нулевого элемента кольца
        public T zero() {
            return additiveGroup.identity();
        }

        // Метод для получения единичного элемента кольца
        public T one() {
            return multiplicativeMonoid.identity();
        }
    }

    // Класс для группы по сложению булевых значений
    static class BooleanAdditiveGroup implements Group<Boolean> {
        @Override
        public Boolean combine(Boolean a, Boolean b) {
            return a || b; // Операция сложения - логическое ИЛИ
        }

        @Override
        public Boolean identity() {
            return false; // Нулевой элемент - false
        }

        @Override
        public Boolean inverse(Boolean a) {
            return !a; // Обратный элемент - отрицание
        }
    }

    // Класс для моноида по умножению булевых значений
    static class BooleanMultiplicativeMonoid implements Monoid<Boolean> {
        @Override
        public Boolean combine(Boolean a, Boolean b) {
            return a && b; // Операция умножения - логическое И
        }

        @Override
        public Boolean identity() {
            return true; // Единичный элемент - true
        }
    }

    // Класс для кольца над логическими значениями
    static class BooleanRing {
        private Ring<Boolean> ring;

        // Конструктор класса
        public BooleanRing() {
            // Создаем группу по сложению и моноид по умножению для булевых значений
            Group<Boolean> additiveGroup = new BooleanAdditiveGroup();
            Monoid<Boolean> multiplicativeMonoid = new BooleanMultiplicativeMonoid();
            // Инициализируем кольцо
            ring = new Ring<>(additiveGroup, multiplicativeMonoid);
        }

        // Метод для сложения элементов кольца
        public boolean add(boolean a, boolean b) {
            return ring.add(a, b);
        }

        // Метод для умножения элементов кольца
        public boolean multiply(boolean a, boolean b) {
            return ring.multiply(a, b);
        }

        // Метод для нахождения обратного элемента по сложению
        public boolean negate(boolean a) {
            return ring.negate(a);
        }

        // Метод для получения нулевого элемента кольца
        public boolean zero() {
            return ring.zero();
        }

        // Метод для получения единичного элемента кольца
        public boolean one() {
            return ring.one();
        }
    }

    // Класс для матрицы над кольцом
    static class Matrix<T> {
        private T[][] data; // Двумерный массив элементов кольца

        // Конструктор класса
        public Matrix(T[][] data) {
            this.data = data;
        }

        // Метод для получения элемента матрицы по индексам
        public T getElement(int row, int col) {
            return data[row][col];
        }

        // Метод для установки значения элемента матрицы по индексам
        public void setElement(int row, int col, T value) {
            data[row][col] = value;
        }

        // Метод для получения количества строк матрицы
        public int getRowCount() {
            return data.length;
        }

        // Метод для получения количества столбцов матрицы
        public int getColumnCount() {
            return data[0].length;
        }
    }

    // Класс для полинома над кольцом
    static class Polynomial<T> {
        private List<T> coefficients; // Список коэффициентов полинома

        // Конструктор класса
        public Polynomial(List<T> coefficients) {
            this.coefficients = coefficients;
        }

        // Метод для получения коэффициента полинома по степени
        public T getCoefficient(int power) {
            return coefficients.get(power);
        }

        // Метод для установки значения коэффициента полинома по степени
        public void setCoefficient(int power, T value) {
            coefficients.set(power, value);
        }

        // Метод для получения степени полинома
        public int getDegree() {
            return coefficients.size() - 1;
        }
    }

    // часть 2
    // Интерфейс группы
    interface Group<T> extends Monoid<T> {
        T inverse(T a);
    }

    // Реализация группы со сложением
    static class DoubleAdditiveGroup implements Group<Double> {
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

    // Реализация группы со сложением
    static class IntegerAdditiveGroup implements Group<Integer> {
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

    // Реализация группы с умножением
    static class MultiplicativeGroup implements Group<Double> {
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

    public static class PrefixSum<T> {
        private T[] prefixSum;

        // Конструктор класса, который инициализирует массив префиксных сумм
        public PrefixSum(T[] nums, Group<T> group) {
            int n = nums.length;
            prefixSum = (T[]) new Number[n + 1]; // Мы используем Number как базовый тип, чтобы обеспечить совместимость с разными типами данных
            prefixSum[0] = group.identity();
            for (int i = 1; i <= n; i++) {
                prefixSum[i] = group.combine(prefixSum[i - 1], nums[i - 1]);
            }
        }

        // Метод для вычисления суммы на отрезке [left, right]
        public T rangeSum(int left, int right, Group<T> group) {
            return group.combine(group.inverse(prefixSum[left]), prefixSum[right + 1]);
        }
    }

    // часть 3

    public static void  main(String[] args) {
        // часть 1
        {
            // часть 1
            Integer[] arrayMax = {3, 1, 4, 1, 5, 9, 2, 6};
            Float[] arrayMin = {-3f, 1f, 4f, 1f, 5f, 9f, 2f, 6f};

            // Создаем экземпляры моноидов для операций максимума и минимума
            Monoid<Integer> maxMonoid = new MonoidMax();
            Monoid<Float> minMonoid = new MonoidMin();

            // Создаем деревья отрезков
            SegmentTree<Integer> maxSegmentTree = new SegmentTree<>(arrayMax, maxMonoid);
            SegmentTree<Float> minSegmentTree = new SegmentTree<>(arrayMin, minMonoid);

            int left = 0;
            int right = 5;
            // Выполняем запрос на дереве отрезков для операции максимума
            int maxResult = maxSegmentTree.query(left, right);
            System.out.println("Max on [" + left + ", " + right + "]: " + maxResult); // Ожидаемый результат: 5

            float minResult = minSegmentTree.query(left, right); // Запрос на отрезке [2, 6]
            System.out.println("Min on [" + left + ", " + right + "]: " + minResult); // Ожидаемый результат: 1


            // Создаем экземпляр моноида для операции умножения
            Monoid<BigInteger> multiplyMonoid = new MonoidMultiply();

            // Быстрое возведение числа в степень
            BigInteger base = BigInteger.valueOf(2);
            int exponent = 100;
            BigInteger result = power(multiplyMonoid, base, exponent);

            // Выводим результат
            System.out.println(base + " in pow " + exponent + " = " + result);

            System.out.println();
        }

        // часть 2
        {
            System.out.println();

            // Группа со сложением
            Group<Double> additiveGroup = new DoubleAdditiveGroup();
            double value = 5;
            System.out.println("Recursive element to " + value + " on adding: " + additiveGroup.inverse(value));

            // Группа с умножением
            Group<Double> multiplicativeGroup = new MultiplicativeGroup();
            System.out.println("Recursive element to " + value + " on multiplying: " + multiplicativeGroup.inverse(value));

            Double[] nums = {1d, 2d, 3d, 4d, 5d};
            Group<Double> additiveGroup2 = new DoubleAdditiveGroup();
            PrefixSum<Double> ps = new PrefixSum(nums, additiveGroup2);

            // Примеры запросов на отрезке
            System.out.println("Sum on [2, 4]: " + ps.rangeSum(2, 4, additiveGroup2));
            System.out.println("Sum on [0, 2]: " + ps.rangeSum(0, 2, additiveGroup2));
        }

        // часть 3
        {
// Реализация кольца над целыми числами
            System.out.println();

            Group<Integer> additiveGroup = new IntegerAdditiveGroup();
            Monoid<Integer> multiplicativeMonoid = new IntegerMonoidMultiply();
            Ring<Integer> integerRing = new Ring(additiveGroup, multiplicativeMonoid);

            // Примеры использования кольца над целыми числами
            Integer a = 5, b = 3;
            System.out.println("Add: " + integerRing.add(a, b));
            System.out.println("Mul: " + integerRing.multiply(a, b));
            System.out.println("Revert on Add: " + integerRing.negate(a));
            System.out.println("Zero: " + integerRing.zero());
            System.out.println("One: " + integerRing.one());

            System.out.println();

            // Реализация кольца над логическими значениями
            BooleanRing booleanRing = new BooleanRing();

            // Примеры использования кольца над логическими значениями
            boolean x = true, y = false;
            System.out.println("Add: " + booleanRing.add(x, y));
            System.out.println("Mul: " + booleanRing.multiply(x, y));
            System.out.println("Revert on Add: " + booleanRing.negate(x));
            System.out.println("Zero: " + booleanRing.zero());
            System.out.println("One: " + booleanRing.one());

            System.out.println();

            // Пример реализации матрицы над кольцом
            Integer[][] matrixData = {{1, 2}, {3, 4}};
            Matrix<Integer> matrix = new Matrix(matrixData);

            // Примеры использования матрицы над кольцом
            System.out.println("Element: " + matrix.getElement(0, 1));
            matrix.setElement(0, 1, 5);
            System.out.println("Changed element: " + matrix.getElement(0, 1));
            System.out.println("Count of rows: " + matrix.getRowCount());
            System.out.println("Count of column: " + matrix.getColumnCount());

            System.out.println();

            // Пример реализации полинома над кольцом
            List<Integer> coefficients = Arrays.asList(1, 0, -2); // 1 + 0*x - 2*x^2
            Polynomial<Integer> polynomial = new Polynomial(coefficients);

            System.out.println();

            // Примеры использования полинома над кольцом
            System.out.println("Polynomial coefficient: " + polynomial.getCoefficient(2));
            polynomial.setCoefficient(2, 3);
            System.out.println("Changed polynomial coefficient: " + polynomial.getCoefficient(2));
            System.out.println("Degree of polynomial: " + polynomial.getDegree());
        }
    }
}

