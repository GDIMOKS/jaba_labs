package lab2;
import lab2.GroupClass.*;

public class PrefixSumClass<T> {
    private T[] prefixSum;

    // ����������� ������, ������� �������������� ������ ���������� ����
    public PrefixSumClass(T[] nums, IGroup<T> group) {
        int n = nums.length;
        prefixSum = (T[]) new Number[n + 1];
        prefixSum[0] = group.identity();
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = group.combine(prefixSum[i - 1], nums[i - 1]);
        }
    }

    // ����� ��� ���������� ����� �� ������� [left, right]
    public T rangeSum(int left, int right, IGroup<T> group) {
        return group.combine(group.inverse(prefixSum[left]), prefixSum[right + 1]);
    }
}
