package lab2.part1;
import lab2.part1.MonoidClass.*;
import lab2.part1.GroupClass.*;

public class RingClass {

    // ����� ������
    public static class Ring<T> {
        private IGroup<T> additiveGroup; // ������ �� ��������
        private IMonoid<T> multiplyMonoid; // ������ �� ���������

        // ����������� ������
        public Ring(IGroup<T> additiveGroup, IMonoid<T> multiplyMonoid) {
            this.additiveGroup = additiveGroup;
            this.multiplyMonoid = multiplyMonoid;
        }

        // ����� ��� �������� ��������� ������
        public T add(T a, T b) {
            return additiveGroup.combine(a, b);
        }

        // ����� ��� ��������� ��������� ������
        public T multiply(T a, T b) {
            return multiplyMonoid.combine(a, b);
        }

        // ����� ��� ���������� ��������� �������� �� ��������
        public T negate(T a) {
            return additiveGroup.inverse(a);
        }

        // ����� ��� ��������� �������� �������� ������
        public T zero() {
            return additiveGroup.identity();
        }

        // ����� ��� ��������� ���������� �������� ������
        public T one() {
            return multiplyMonoid.identity();
        }

        public void print(T a, T b) {
            System.out.println("��������: " + this.add(a, b));
            System.out.println("���������: " + this.multiply(a, b));
            System.out.println("�������� ������� �� ��������: " + this.negate(a));
            System.out.println("������� �������: " + this.zero());
            System.out.println("��������� �������: " + this.one());
//            System.out.println("������: {" +
//                    "���������� ������: " + this.additiveGroup +
//                    ", ����������������� ������: " + this.multiplyMonoid +
//                    '}');
            System.out.println();
        }

    }


}
