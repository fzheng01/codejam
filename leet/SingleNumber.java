public class SingleNumber {
    public int singleNumber(int[] A) {
        int result = 0;
        for(int a : A)
            result ^= a;
        return result;
    }
    
    public static void main(String[] args) {
        SingleNumber sn = new SingleNumber();
        int[] x = {5, 3, 4, 10, 3, 10, 5, 11, 4};
        System.out.println(sn.singleNumber(x));
    }
}