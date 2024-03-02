public class QuadraticSolver {

    /**
     * Реализуйте алгоритм нахождения всех <b>целых</b> корней квадратного уравнения
     * вида <code>x^2 + px + q = 0</code>. Найденные корни выведите в поток
     * стандартного вывода методом {@link java.io.PrintStream#print(long)} объекта
     * {@link java.lang.System#out}. Корни можно выводить в любом порядке. Ошибки
     * можно игнорироввать. Если у квадратного уравнения нету целых корней, следует вывести
     * соответствующее сообщение.
     *
     * @param args массив с коэфицентами полинома в текстовом представлении.
     */
    public static void main(String[] args) {
        long p = Long.parseLong(args[0]);
        long q = Long.parseLong(args[1]);

        long discriminant = p*p - 4*q;

        long sqrt = squareRoot(discriminant);

        System.out.println();

        if (sqrt == -1) {
            System.out.println("Нет целых решений уравнения");
        } else {
            long x1, x2;

            x1 = (-p + sqrt) / 2;
            x2 = (-p - sqrt) / 2;

            System.out.println("Квадратный корень из дискриминанта " + discriminant + " = " + sqrt);
            System.out.println("Корни уравнения x^2 " + (p > 0 ? (" + " + p) : (" - " + p*(-1)) ) + "x + " + q + " = 0 : \nx1 = " + x1 + "\nx2 = " + x2);
        }

        System.out.println();
    }

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
