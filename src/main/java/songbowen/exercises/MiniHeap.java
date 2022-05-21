package songbowen.exercises;

/**
 * 最小堆
 */
public class MiniHeap {
    private int[] heapArray;
    private int currentSize;

    public MiniHeap(int size) {
        heapArray = new int[size];
    }

    /**
     * 是否是页节点
     */
    public boolean isLeaf(int pos) {
        return pos * 2 + 1 >= currentSize;
    }

    /**
     * 左孩子位置
     */
    public int leftChild(int pos) {
        return pos * 2 + 1;
    }

    /**
     * 右孩子位置
     */
    public int rightChild(int pos) {
        return pos * 2 + 2;
    }

    /**
     * 父节点位置
     */
    public int parent(int pos) {
        return (pos - 1) / 2;
    }

    private boolean compareAndSwapWhenBigger(int pos, int target) {
        if (heapArray[pos] > heapArray[target]) {
            int tmp = heapArray[pos];
            heapArray[pos] = heapArray[target];
            heapArray[target] = tmp;
            return true;
        } else {
            return false;
        }
    }

    private boolean compareAndSwapWhenSmaller(int pos, int target) {
        if (heapArray[pos] < heapArray[target]) {
            int tmp = heapArray[pos];
            heapArray[pos] = heapArray[target];
            heapArray[target] = tmp;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 从pos开始向下调整使序列成为堆
     */
    private void siftDown(int pos) {
        if (isLeaf(pos)) {
            return;
        }
        int leftChild = leftChild(pos);
        int rightChild = rightChild(pos);
        if (rightChild >= currentSize) {
            compareAndSwapWhenBigger(pos, leftChild);
        } else {
            boolean swap;
            if (heapArray[leftChild] < heapArray[rightChild]) {
                swap = compareAndSwapWhenBigger(pos, leftChild);
                if (swap) {
                    siftDown(leftChild);
                }
            } else {
                swap = compareAndSwapWhenBigger(pos, rightChild);
                if (swap) {
                    siftDown(rightChild);
                }
            }
        }
    }

    /**
     * 从pos开始向上调整使序列成为堆
     */
    private void siftUP(int pos) {
        if (pos == 0) {
            return;
        }
        int parent = parent(pos);
        if (compareAndSwapWhenSmaller(pos, parent)) {
            siftUP(parent);
        }
    }

    /**
     * 删除指定位置元素
     * <p>
     * 用最后一个元素替换指定位置的元素，从指定位置开始向上调整，将堆size减一
     */
    public boolean remove(int pos) {
        if (pos >= currentSize) {
            return false;
        }
        currentSize--;
        if (pos == currentSize) {
            return true;
        }
        heapArray[pos] = heapArray[currentSize];
        siftUP(pos);
        return true;
    }

    /**
     * 向堆中插入新元素
     */
    public boolean insert(int value) {
        if (currentSize == heapArray.length) {
            return false;
        }
        heapArray[currentSize] = value;
        siftUP(currentSize);
        return true;
    }

    /**
     * 从堆顶删除最小元素
     */
    public void removeMin() {
        heapArray[0] = heapArray[currentSize - 1];
        siftDown(0);
    }


}
