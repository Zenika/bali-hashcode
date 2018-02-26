package hashcode;

import java.util.Scanner;

public class Main {
    public void main(String[] argv) {
        Scanner scanner = new Scanner(System.in);
        Pizza pizza = new Pizza();
        pizza.rows = scanner.nextInt();
        pizza.columns = scanner.nextInt();
        pizza.min = scanner.nextInt();
        pizza.max = scanner.nextInt();

        pizza.cells = new Boolean[pizza.rows][pizza.columns];
        String line;
        for (int i = 0; i < pizza.rows; i++) {
            line = scanner.nextLine();
            for (int j = 0; j < pizza.rows; j++) {
                pizza.cells[i][j] = line.charAt(j) == 'T';
            }
        }
        // TODO Algo
        System.out.println(pizza.slices.size());
        for (Slice slice : pizza.slices) {
            System.out.printf("%d %d %d %d %n", slice.r1, slice.c1, slice.r2, slice.c2);
        }
    }
}
