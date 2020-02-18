package hashcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] argv) {
        Scanner scanner = new Scanner(System.in);

        String[] firstLine = scanner.nextLine().split(" ");
        int requestedSlices = Integer.parseInt(firstLine[0]);

        List<String> solution = new ArrayList<>();
        int count = 0;
        int sumOfSlices = 0;
        while (scanner.hasNext()) {
            int slices = scanner.nextInt();
            if (sumOfSlices + slices > requestedSlices) {
                break;
            }
            solution.add(Integer.toString(count));
            sumOfSlices += slices;
            count++;
        }

        print(solution);
    }

    private static void print(List<String> solution) {
        System.out.println(solution.size());
        System.out.print(String.join(" ", solution));
    }
}
