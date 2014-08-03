public class CountOnes {
    private static int countOnes(int a) {
        int counter = 0, b;
        do {
            b = a >> 1;
            if (a-2*b == 1) counter++;
            a = b;
        } while(a > 0);
        return counter;
    }
    public static void main(String[] args) {
        if (args.length < 1) throw new IllegalArgumentException("input one integer");
        try {
            System.out.println(countOnes(Integer.parseInt(args[0])));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}