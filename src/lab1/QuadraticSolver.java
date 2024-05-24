package lab1;

public class QuadraticSolver {

    /**
     * Реализуйте вспомогательную функцию для вычисления квадратного корня из числа
     * {@code n}. Запрещено использовать готовые библиотечные реализации. Алгоритм
     * должен быть основан на бинарном поиске, методе ньютона или аналогах.
     *
     * @param n целое число
     * @return квадратный корень из числа {@code n}, либо {@code -1}, если {@code n}
     *         не является полным квадратом. Также можете в этом методе заодно
     *         проверять, что {@code n} - неотрицательное число, и возвращать
     *         {@code -1} в противном случае.
     */
    public static long squareRoot(long n) {
        if (n < 0)
            return -1;

        long x = n;
        long y = 1; // y = n/xn

        while (x - y > 1) {
            x = (x + y) / 2;
            y = n / x;
        }

        return checkLong(x, n);
    }

    public static long checkLong(long sqrt, long sqr) {
        return (sqrt * sqrt == sqr) ? sqrt : -1;
    }

}
