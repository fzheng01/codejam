interface Work {
    void logHours(int hours);
}

class Job {
    void doOne() {
        System.out.println("One");
    }
}

class Programming extends Job implements Work{
    // must public, as interface is implicitly public abstract
    public void logHours(int hours) {
        System.out.println("Programming logs " + hours + " hours");
    }
    void doTwo() {
        System.out.println("Two");
    }
}

public class ClassInheritance {
    public static void main(String[] args) {
        Job a = new Job();
        Programming b = new Programming();
        Job c = new Programming();
        Work d = new Programming();
        
        ((Programming) c).doTwo();
        ((Work) c).logHours(5);
        
        ((Programming) d).doTwo();
        d.logHours(3);
    }
}