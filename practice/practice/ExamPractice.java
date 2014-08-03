public class ExamPractice {
    public static void main (String[] args) {
        StringBuilder sb = new StringBuilder();
        String h1 = "HelloWorld";
        sb.append("Hello").append("World");
        if (h1.equals(sb.toString())) {
            System.out.println("They really match");
        }
    }
}