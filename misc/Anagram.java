public class Anagram {
    private static final int N = 26;
    public static boolean anagram(String x, String y) {
        String a = x.toLowerCase();
        String b = y.toLowerCase();
        int[] map = new int[N];
        for (int i = 0; i < a.length(); i++) {
            int c = a.charAt(i) - 'a';
            if (c >= 0 && c < N) map[c]++;
        }
        for (int j = 0; j < b.length(); j++) {
            int c = b.charAt(j) - 'a';
            if (c >= 0 && c < N) {
                map[c]--;
                if (map[c] < 0) return false;
            }
        }
        for (int i = 0; i < N; i++) {
            if (map[i] != 0) return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        String x = "Tom Marvolo Riddle";
        String y = "I am Lord Voldemort";
        System.out.println(anagram(x, y));
    }
}