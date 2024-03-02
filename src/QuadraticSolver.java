public class QuadraticSolver {

    /**
     * ���������� �������� ���������� ���� <b>�����</b> ������ ����������� ���������
     * ���� <code>x^2 + px + q = 0</code>. ��������� ����� �������� � �����
     * ������������ ������ ������� {@link java.io.PrintStream#print(long)} �������
     * {@link java.lang.System#out}. ����� ����� �������� � ����� �������. ������
     * ����� �������������. ���� � ����������� ��������� ���� ����� ������, ������� �������
     * ��������������� ���������.
     *
     * @param args ������ � ������������ �������� � ��������� �������������.
     */
    public static void main(String[] args) {
        long p = Long.parseLong(args[0]);
        long q = Long.parseLong(args[1]);

        System.out.println(p * q);
        long discriminant = p*p - 4*q;

        long sqrt = squareRoot(discriminant);

        long x1, x2;

        x1 = (-p + sqrt) / 2;
        x2 = (-p - sqrt) / 2;

        if (sqrt == -1) {
            System.out.println("��� ����� �������");
        } else {



            System.out.println("���������� ������ �� " + discriminant + " = " + sqrt);
        }

    }

    /**
     * ���������� ��������������� ������� ��� ���������� ����������� ����� �� �����
     * {@code n}. ��������� ������������ ������� ������������ ����������. ��������
     * ������ ���� ������� �� �������� ������, ������ ������� ��� ��������.
     *
     * @param n ����� �����
     * @return ���������� ������ �� ����� {@code n}, ���� {@code -1}, ���� {@code n}
     *         �� �������� ������ ���������. ����� ������ � ���� ������ ������
     *         ���������, ��� {@code n} - ��������������� �����, � ����������
     *         {@code -1} � ��������� ������.
     */
    public static long squareRoot(long n) {
        if (n < 0)
            return -1;

        if (n == 0 || n == 1)
            return n;

        double x = n;
        double y = 1; // y = n/xn
        double epsilon = 0.000001; // �������� ����������

        while (x - y > epsilon) {
            x = (x + y) / 2;
            y = n / x;
        }

        long longX = (long) x;
        return (longX * longX == n) ? longX : -1;
    }

    public static long CheckLong(double a) {
        long longX = (long) a;
        return (longX * longX == a) ? longX : -1;
    }

}
