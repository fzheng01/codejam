import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;

public class JumbleSort {
    public static void main(String[] args) {
        final String CHARSET_NAME = "UTF-8";
        List<Integer> itemInteger = new ArrayList<Integer>();
        List<String> itemString = new ArrayList<String>();
        System.out.println("Input: ");
        Scanner sc = new Scanner(System.in, CHARSET_NAME);
        if (sc.hasNextLine()) {
            String inputStr = sc.nextLine();
            String[] items = inputStr.split("\\s+");
            int itemNum = items.length;
            int[] itemType = new int[itemNum];
            for (int i = 0; i < itemNum; i++) {
                if (items[i].matches("-?\\d+")) {
                    itemInteger.add(Integer.parseInt(items[i]));
                    itemType[i] = 1;
                } else {
                    itemString.add(items[i]);
                    itemType[i] = 0;
                }
            }
            Collections.sort(itemInteger);
            Collections.sort(itemString);
            System.out.println("Output: ");
            Iterator<Integer> iteratorInt = itemInteger.iterator();
            Iterator<String> iteratorStr = itemString.iterator();
            for (int i = 0; i < itemNum; i++) {
                if (itemType[i] == 1) {
                    System.out.print(iteratorInt.next());
                } else {
                    System.out.print(iteratorStr.next());
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        sc.close();
    }
}