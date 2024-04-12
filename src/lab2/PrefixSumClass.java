package lab2;
import lab2.GroupClass.*;

public class PrefixSumClass<T> {
        private T[] prefixSum;

        // Конструктор класса, который инициализирует массив префиксных сумм
        public PrefixSumClass(T[] nums, Group<T> group) {
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
