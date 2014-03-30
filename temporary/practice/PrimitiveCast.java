class Super {
    static String ID = "QBANK";
}

class Sub extends Super {
    static {
        System.out.print("In Sub");
    }
}

class Sub2 {
    static {
        System.out.println("Sub2 inited");
    }
}

public class PrimitiveCast {
    public static void main(String[] args) {
        char c = 'a';
        short s = (short) c;
        int i = c;
        System.out.println(s);
        System.out.println(Sub.ID);
        Sub2 sub2 = new Sub2();
    }
}