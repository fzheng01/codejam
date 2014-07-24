public class Singleton {
    private static Singleton _instance = null;
    protected Singleton() {
    }
    public static Singleton getInstance() {
        if (_instance == null) {
            _instance = new Singleton();
        }
        return _instance;
    }
    public static void main(String[] args) {
        Singleton A = Singleton.getInstance();
        Singleton B = Singleton.getInstance();
        System.out.println(A == B);
    }
}