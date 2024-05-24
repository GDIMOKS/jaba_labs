package lab2;
import lab2.MonoidClass.*;
import lab2.GroupClass.*;

public  class RingClass {

    // ����� ������
    public abstract static class Ring<T> {
        protected IGroup<T> additiveGroup; // ������ �� ��������
        protected IMonoid<T> multiplyMonoid; // ������ �� ���������

        // ����������� ������
        public Ring(IGroup<T> additiveGroup, IMonoid<T> multiplyMonoid) {
            this.additiveGroup = additiveGroup;
            this.multiplyMonoid = multiplyMonoid;
        }

        // ����� ��� �������� ��������� ������
        public abstract T add(T a, T b) ;

        // ����� ��� ��������� ��������� ������
        public abstract T multiply(T a, T b);

        // ����� ��� ���������� ��������� �������� �� ��������
        public abstract T negate(T a);

        // ����� ��� ��������� �������� �������� ������
        public abstract T zero();

        // ����� ��� ��������� ���������� �������� ������
        public abstract T one();

        public abstract void print(T a, T b);

    }

    public static class StandardRing<T> extends Ring<T> {

        public StandardRing(IGroup<T> additiveGroup, IMonoid<T> multiplyMonoid) {
            super(additiveGroup, multiplyMonoid);
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

    public static class MatrixRing<T> extends Ring<T> {

        private Ring<T> standardRing;
        private int size;

        public MatrixRing(IGroup<T> additiveGroup, IMonoid<T> multiplyMonoid, int size) {
            super(additiveGroup, multiplyMonoid);
            this.size = size;
        }

        public MatrixRing(Ring<T> ring) {
            super(ring.additiveGroup, ring.multiplyMonoid);
            this.size = size;
        }

        // ����� ��� �������� ��������� ������
        public MatrixClass<T> add(MatrixClass<T> a, MatrixClass<T> b) {
            return matrixOperation(a, b, MatrixClass.Operation.Add, null);
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

        private MatrixClass<T> matrixOperation(MatrixClass<T> a, MatrixClass<T> b, MatrixClass.Operation operationType, T scalar) {
            int rows = a.getRowCount();
            int cols = a.getColumnCount();
            T[][] result = (T[][]) new Object[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    switch (operationType) {
                        case Add:
                            result[i][j] = standardRing.add(a.data[i][j], b.data[i][j]);
                            break;
                        case Subtract:
                            result[i][j] = standardRing.add(a.getElement(i, j), standardRing.negate(b.getElement(i, j)));
                            break;
                        case Multiply:
                            result[i][j] = standardRing.zero();
                            for (int k = 0; k < a.getColumnCount(); k++) {
                                result[i][j] = standardRing.add(result[i][j], standardRing.multiply(a.data[i][k], b.data[k][j]));
                            }
                            break;
                        case ScalarMultiply:
                            result[i][j] = standardRing.multiply(a.data[i][j], scalar);
                            break;
                    }
                }
            }

            return new MatrixClass<>(ring, result);
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
