public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int div = 1;
        do {
            div *= 10;
        } while (x/div >= 10);
        while (x > 0) {
            if (x >= div && x/div != x%10) {
                return false;
            }
            x = (x%div)/10;
            div /= 100;
        }
        return true;
    }
    
    public static void main(String[] args) {
        PalindromeNumber pn = new PalindromeNumber();
        System.out.println(pn.isPalindrome(1234321));
    }
}