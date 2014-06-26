import java.util.Arrays;

public class MergeSortStandard {
    protected int[] array;
    
    public MergeSortStandard(int[] a) {
        array = a;
    }
    
    public String toString() {
        return Arrays.toString(array);
    }
    
    public void mergeSort(int start, int end) {
        int middle;
        if (start > end || start < 0 || end >= array.length) {
            throw new ArrayIndexOutOfBoundsException("");
        }
        if (end > start) {
            middle = (start + end)/2;
            mergeSort(start, middle);
            mergeSort(middle+1, end);
            doMerge(start, middle+1, end);
        }
    }
    
    public void doMerge(int lStart, int rStart, int end) {
        int[] tmpArray = new int[end-lStart+1];
        int t = 0;
        int i = lStart;
        int j = rStart;
        while (i < rStart && j <= end) {
            if (array[i] <= array[j]) {
                tmpArray[t++] = array[i++];
            } else {
                tmpArray[t++] = array[j++];
            }
        }
        if (i > rStart-1) {
            while (j <= end) {
                tmpArray[t++] = array[j++];
            }
        }
        if (j > end) {
            while (i < rStart) {
                tmpArray[t++] = array[i++];
            }
        }
        t = 0;
        i = lStart;
        while (t <= end-lStart) {
            array[i++] = tmpArray[t++];
        }
    }
}
