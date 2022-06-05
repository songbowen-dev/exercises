package songbowen.exercises;

import java.util.Arrays;

/**
 * 排序算法
 */
public class Sort {

    static int[] genTestData() {
        return new int[]{1, 69, 25, 14, 78, 6, 9, 9, 45, 56};
    }

    static void swap(int[] data, int index1, int index2) {
        int tmp = data[index1];
        data[index1] = data[index2];
        data[index2] = tmp;
    }

    /**
     * 插入排序
     */
    static void insertSort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = i; j > 0; j--) {
                if (data[j] < data[j - 1]) {
                    swap(data, j, j - 1);
                }
            }
        }
    }

    static int partition(int[] data, int begin, int length) {
        int pivotValue = data[begin];
        int leftIndex = begin;
        int rightIndex = begin + length - 1;
        boolean flag = true;
        while (leftIndex < rightIndex) {
            if (flag) {
                if (data[rightIndex] < pivotValue) {
                    data[leftIndex] = data[rightIndex];
                    leftIndex++;
                    flag = false;
                } else {
                    rightIndex--;
                }
            } else {
                if (data[leftIndex] > pivotValue) {
                    data[rightIndex] = data[leftIndex];
                    rightIndex--;
                    flag = true;
                } else {
                    leftIndex++;
                }
            }
        }
        data[leftIndex] = pivotValue;
        return leftIndex;
    }

    static int partitionV2(int[] data, int begin, int length) {
        int pivotValue = data[begin];
        int leftIndex = begin;
        int rightIndex = begin + length - 1;
        while (leftIndex < rightIndex) {
            while (data[rightIndex] > pivotValue && leftIndex < rightIndex) {
                rightIndex--;
            }
            if (leftIndex < rightIndex) {
                data[leftIndex] = data[rightIndex];
                leftIndex++;
            }
            while (data[leftIndex] < pivotValue && leftIndex < rightIndex) {
                leftIndex++;
            }
            if (leftIndex < rightIndex) {
                data[rightIndex] = data[leftIndex];
                rightIndex--;
            }
        }
        data[leftIndex] = pivotValue;
        return leftIndex;
    }

    /**
     * 快速排序
     */
    static void quickSort(int[] data, int begin, int length) {
        int pivot = partition(data, begin, length);

        int leftLength = pivot - begin;
        if (leftLength > 1) {
            quickSort(data, begin, leftLength);
        }
        int rightLength = begin + length - 1 - pivot;
        if (rightLength > 1) {
            quickSort(data, pivot + 1, rightLength);
        }
    }

    /**
     * 堆排序
     * 时间复杂度: O(nlog(n)), 空间复杂度: O(1)
     */
    static void heapSort(int[] data) {
        MiniHeap miniHeap = new MiniHeap(data, data.length);
        while (miniHeap.currentSize > 0) {
            int tmp = data[0];
            data[0] = data[miniHeap.currentSize - 1];
            data[miniHeap.currentSize - 1] = tmp;
            miniHeap.currentSize--;
            miniHeap.siftDown(0);
        }
    }

    static void merge(int[] data, int leftBegin, int leftLength, int rightBegin, int rightLength) {
        int[] tmp1 = new int[leftLength];
        for (int i = 0; i < leftLength; i++) {
            tmp1[i] = data[leftBegin + i];
        }
        int[] tmp2 = new int[rightLength];
        for (int i = 0; i < rightLength; i++) {
            tmp2[i] = data[rightBegin + i];
        }
        int index1 = 0, index2 = 0;
        int index3 = leftBegin;
        while (index1 < leftLength && index2 < rightLength) {
            if (tmp1[index1] < tmp2[index2]) {
                data[index3++] = tmp1[index1++];
            } else {
                data[index3++] = tmp2[index2++];
            }
        }
        while (index1 < leftLength) {
            data[index3++] = tmp1[index1++];
        }
        while (index2 < rightLength) {
            data[index3++] = tmp2[index2++];
        }
    }

    /**
     * 归并排序
     * 时间复杂度O(nlog(n)) 空间复杂度O(n)
     * 是稳定的
     */
    static void mergeSort(int[] data, int begin, int length) {
        int middle = begin + length / 2;
        int leftLength = middle - begin;
        if (leftLength > 1) {
            mergeSort(data, begin, leftLength);
        }
        int rightLength = begin + length - middle;
        if (rightLength > 1) {
            mergeSort(data, middle, rightLength);
        }
        merge(data, begin, leftLength, middle, rightLength);
    }


    public static void main(String[] args) {
        int[] testData = genTestData();
        mergeSort(testData, 0, testData.length);
        System.out.println(Arrays.toString(testData));
    }
}
