import java.util.*;

public class RemoveElement {
    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    // main method
    public static int removeElement(int[] A, int elem) { 
        int i, j;
        for (i = 0, j = A.length - 1; i <= j; i++) {
            if (A[i] != elem) continue;
            while (i <= j && A[j] == elem) {
                j--;
            }
            if (i > j) break;
            else {
                swap(A, i, j);
                j--;
            }
        }
        return i;
    }
    public static void main(String[] args) {
        int[] x = {2, 3, 3, 4, 6};
        System.out.println(removeElement(x, 3));
        System.out.println(Arrays.toString(x));
    }
}