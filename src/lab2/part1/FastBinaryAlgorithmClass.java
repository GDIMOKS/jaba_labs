package lab2.part1;
import lab2.part1.MonoidClass.*;

public class FastBinaryAlgorithmClass {
    public static <T> T power(IMonoid<T> monoid, T base, int exponent) {
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
}
