package lab2;
import lab2.MonoidClass.*;

public class SegmentTree<T> {
        private T[] tree;
        private T[] array;
        private IMonoid<T> monoid;
        private int size;

        public SegmentTree(T[] array, IMonoid<T> monoid) {
            this.array = array;
            this.monoid = monoid;
            size = array.length;

            int height = (int) Math.ceil(Math.log(size) / Math.log(2));
            int maxSize = 2 * (int) Math.pow(2, height) - 1;
            tree = (T[]) new Object[maxSize];
            buildTree(0, 0, size - 1);
        }

    private void buildTree(int index, int start, int end) {
        if (start == end) {
            tree[index] = array[start];
        } else {
            int mid = start + (end - start) / 2;
            int leftIndex = 2 * index + 1;
            int rightIndex = 2 * index + 2;
            buildTree(leftIndex, start, mid);
            buildTree(rightIndex, mid + 1, end);
            tree[index] = monoid.combine(tree[leftIndex], tree[rightIndex]);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        printTree(0, sb, "");
        return sb.toString();
    }

    private void printTree(int index, StringBuilder sb, String prefix) {
        if (index < tree.length) {
            sb.append(prefix).append("|-- ").append(tree[index]).append("\n");
            String childPrefix = prefix + "|   ";
            printTree(2 * index + 1, sb, childPrefix);
            printTree(2 * index + 2, sb, childPrefix);
        }
    }
}
