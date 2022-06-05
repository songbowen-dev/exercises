package songbowen.exercises;

public class Array {

    /**
     * 题目：输入一个递增排序的数组和一个值k
     * ，请问如何在数组中找出两个和为k
     * 的数字并返回它们的下标？假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。例如，输入数组[1，2，4，6，10]，k
     * 的值为8，数组中的数字2与6的和为8，它们的下标分别为1与3
     */
    static void towSum(int[] in, int k) {
        int index1 = 0;
        int index2 = in.length - 1;

        while (index1 != index2) {
            int sum = in[index1] + in[index2];
            if (sum == k) {
                System.out.println(index1 + "," + index2);
                return;
            } else if (sum < k) {
                index1++;
            } else {
                index2--;
            }
        }
    }

    public static void main(String[] args) {
        towSum(new int[]{1, 2, 3, 4, 6, 8, 9}, 8);
        towSum(new int[]{1, 2, 3, 4, 6, 8, 9}, 5);
    }
}
