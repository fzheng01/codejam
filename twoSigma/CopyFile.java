import java.util.Scanner;
import java.util.ArrayList;

public class CopyFile {
    public static void main(String[] args) {
        final String CHARSET_NAME = "UTF-8";
        System.out.println("Input: ");
        Scanner sc = new Scanner(System.in, CHARSET_NAME);
        int nDataCenter = Integer.parseInt(sc.nextLine());
        int i = nDataCenter;
        ArrayList<Integer> dataSets = new ArrayList<Integer>();
        ArrayList<int[]> dataCentersArray = new ArrayList<int[]>();
        int dataCenterId = 1;
        while (i-- > 0) {
            String inputStr = sc.nextLine();
            String[] center = inputStr.split("\\s+");
            int centerVolume = center.length;
            for (int j = 0; j < centerVolume; j++) {
                int k;
                int dataSetIdInCenter = Integer.parseInt(center[j]);
                for (k = 0; k < dataSets.size(); k++) {
                    if (dataSets.get(k).equals(dataSetIdInCenter)) {
                        int[] data = dataCentersArray.get(k);
                        data[0] = dataCenterId;
                        data[dataCenterId] = 1;
                        break;
                    }
                }
                if (k == dataSets.size()) {
                    dataSets.add(dataSetIdInCenter);
                    int[] data = new int[nDataCenter+1];
                    data[0] = dataCenterId;
                    data[dataCenterId] = 1;
                    dataCentersArray.add(data);
                }
            }
            dataCenterId++;
        }
        sc.close();
        System.out.println("Output: ");
        for (i = 0; i < dataSets.size(); i++) {
            int[] data = dataCentersArray.get(i);
            for (int j = 1; j <= nDataCenter; j++) {
                if (data[j] != 1) {
                    System.out.print(dataSets.get(i));
                    System.out.print(" ");
                    System.out.print(data[0]);
                    System.out.print(" ");
                    System.out.print(j);
                    System.out.println();
                }
            }
        }
        System.out.println("done");
    }
}