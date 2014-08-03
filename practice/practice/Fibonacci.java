public class Fibonacci {
    private static long fibonacci(long n) {
        if (n < 0) throw new IllegalArgumentException("n cannot be negative");
        if (n <= 1) return n;
        long pre = 0, cur = 1, i = 2;
        while (i++ <= n) {
            long tmp = cur;
            cur += pre;
            pre = tmp;
        }
        return cur;
    }
    
    public static void main(String[] args) {
        long n = Integer.parseInt(args[0]);
        System.out.println(fibonacci(n));
    }
}