package lab2;
import lab2.MonoidClass.*;

public class SegmentTree<T> {
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
