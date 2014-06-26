public class MergeSortInPlace extends MergeSortStandard {
    public MergeSortInPlace (int[] a) {
        super(a);
    }
    
    protected void shiftElementInArray (int start, int end) {
        int endVal = array[end];
        int i = end;
        while (i > start) {
            array[i] = array[--i];
        }
        array[start] = endVal;
    }
    
    public void doMerge(int lStart, int rStart, int end) {
        while (lStart < end && rStart <= end) {
            if (array[lStart] <= array[rStart]) {
                lStart++;
            }
            if (array[lStart] > array[rStart]) {
                shiftElementInArray(lStart, rStart);
                lStart++;
                rStart++;
            }
        }
    }
}