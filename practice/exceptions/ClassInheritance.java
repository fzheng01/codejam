interface Work {
    void logHours(int hours);
}

class Job {
    protected float rate = 10.1f;
    private boolean isManager;
    boolean isManager() {
        return isManager;
    }
    void setRate(float newRate) {
        this.rate = newRate;
    }
    void doOne() {
        System.out.println("One");
    }
    int getStoryPoint() {
        return 1;
    }
    static int getWorkHours() {
        return 11;
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
    int getStoryPoint() {
        return 2;
    }
    static int getWorkHours() {
        return 12;
    }
}

public class ClassInheritance {
    public static void main(String[] args) {
        Job a = new Job();
        Programming b = new Programming();
        Job c = new Programming();
        Work d = new Programming();
        
        System.out.println("Testing: inheritance and casting");
        ((Programming) c).doTwo();
        ((Work) c).logHours(5);
        ((Programming) d).doTwo();
        d.logHours(3);
        System.out.println();
        
        System.out.println("Testing: polymorphism");
        System.out.println(a.getStoryPoint());
        System.out.println(b.getStoryPoint());
        System.out.println(c.getStoryPoint());
        System.out.println();

        System.out.println("Testing: redefine (static methods)");
        System.out.println(a.getWorkHours());
        System.out.println(b.getWorkHours());
        System.out.println(c.getWorkHours());
        System.out.println();
        
        System.out.println("Testing: instance variable and local variable when not initialized");
        System.out.println(a.isManager()); // instance variable default value
        boolean isLocalManager = true;  // local variable must be initialized
        System.out.println(isLocalManager);
        System.out.println();
        
        System.out.println("Testing: final modifier prevent reassigning, but not changing state");
        final Job e = new Job();
        System.out.println(e.rate);
        e.setRate(10.2f);
        System.out.println(e.rate);
        System.out.println();
    }
}