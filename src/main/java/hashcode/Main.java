package hashcode;

import java.util.Scanner;

public class Main {
    public static void main(String[] argv) {
        Scanner scanner = new Scanner(System.in);
        Pizza pizza = new Pizza();
        pizza.rows = scanner.nextInt();
        pizza.columns = scanner.nextInt();
        pizza.min = scanner.nextInt();
        pizza.max = scanner.nextInt();
        scanner.nextLine();

        pizza.cells = new Boolean[pizza.rows][pizza.columns];
        String line;
        for (int i = 0; i < pizza.rows; i++) {
            line = scanner.nextLine();
            for (int j = 0; j < pizza.columns; j++) {
                pizza.cells[i][j] = line.charAt(j) == 'T';
            }
        }
        // TODO Algo


        pizza.slices.add(new Slice(0, 0, 2, 1));
        pizza.slices.add(new Slice(0, 2, 2, 2));
        pizza.slices.add(new Slice(0, 3, 2, 4));
        System.out.println(pizza.slices.size());
        for (Slice slice : pizza.slices) {
            System.out.printf("%d %d %d %d %n", slice.r1, slice.c1, slice.r2, slice.c2);
        }
    }
}
