public class ReverseInteger {
    public static int reverseInteger(int x) {
        if (x < 10) return x;
        int result = 0;
        while (x > 0) {
            result = result*10 + x%10;
            x = x/10;
        }
        return result;
    }
    public static void main(String[] args) {
        int input = Integer.parseInt(args[0]);
        System.out.println(reverseInteger(input));
    }
}