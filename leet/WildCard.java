public class WildCard {
    public boolean isMatch(String s, String p) {
        return isMatch(s, p, 0, 0);
    }
    
    private boolean isMatch(String s, String p, int sx, int px) {
    }
    
    public static void main(String[] args) {
        String[] a = {"aa", "aa", "aaa", "aa", "aa", "ab", "aab"};
        String[] b = {"a",  "aa", "aa",  "*",  "a*", "?*", "c*a*b"};
        for (int i = 0; i < a.length; i++) {
            System.out.print("isMatch(" + a[i] + ", " + b[i] + ") = ");
            System.out.println(new WildCard().isMatch(a[i], b[i]));
        }
    }
}