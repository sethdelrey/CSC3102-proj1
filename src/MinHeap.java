public class MinHeap {
    private int[] Heap;
    private int size;
    private int maxsize;
    private int k;

    private static final int FRONT = 1;

    public MinHeap(int maxsize, int k) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MIN_VALUE;
        this.k = k;
    }

    private int parent(int pos) {
        return pos/k;
    }

    private int leftChild(int pos) {
        return k*pos;
    }

    private int rightChild(int pos) {
        return (k*pos) + 1;
    }

    private boolean isLeaf(int pos) {
        if (pos >= (size/k) && pos <= size) {
            return true;
        }
        return false;
    }

    //swaps 2 nodes of the heap
    private void swap(int fpos, int spos) {
        int temp;
        temp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = temp;
    }

    private void minHeapify(int pos) {
        //if node is nonleaf & greater than
        //any of its children
        if (!isLeaf(pos)) {
            if (Heap[pos] > Heap[leftChild(pos)] || Heap[pos] > Heap[rightChild(pos)]) {

                //swap w leftchild and heapify left child
                if (Heap[leftChild(pos)] < Heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }
                //swap w rightchild & heapify rightchild
                else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }

    public void insert(int x) {
        if (size >= maxsize) {
            return;
        }
        Heap[++size] = x;
        int curr = size;

        while (Heap[curr] < Heap[parent(curr)]) {
            swap(curr, parent(curr));
            curr = parent(curr);
        }
    }

    public int extractMin() {
        int popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        minHeapify(FRONT);
        return popped;
    }

}
