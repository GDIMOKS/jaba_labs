package lab2;
import lab2.MonoidClass.*;
import lab2.GroupClass.*;

public class RingClass {

    // Класс кольца
    static class Ring<T> {
        private Group<T> additiveGroup; // Группа по сложению
        private Monoid<T> multiplyMonoid; // Моноид по умножению

        // Конструктор класса
        public Ring(Group<T> additiveGroup, Monoid<T> multiplyMonoid) {
            this.additiveGroup = additiveGroup;
            this.multiplyMonoid = multiplyMonoid;
        }

        // Метод для сложения элементов кольца
        public T add(T a, T b) {
            return additiveGroup.combine(a, b);
        }

        // Метод для умножения элементов кольца
        public T multiply(T a, T b) {
            return multiplyMonoid.combine(a, b);
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
            return multiplyMonoid.identity();
        }

        public void print(T a, T b) {
            System.out.println("Сложение: " + this.add(a, b));
            System.out.println("Умножение: " + this.multiply(a, b));
            System.out.println("Обратный элемент по сложению: " + this.negate(a));
            System.out.println("Нулевой элемент: " + this.zero());
            System.out.println("Единичный элемент: " + this.one());
//            System.out.println("Кольцо: {" +
//                    "Аддитивная группа: " + this.additiveGroup +
//                    ", Мультипликативный моноид: " + this.multiplyMonoid +
//                    '}');
            System.out.println();
        }

    }


}
