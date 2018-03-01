package hashcode;

import java.util.Scanner;

public class Main {
    public static void main(String[] argv) {
        Scanner scanner = new Scanner(System.in);
        City city = new City();
        city.rows = scanner.nextInt();
        city.columns = scanner.nextInt();
        city.nbVehicules = scanner.nextInt();
        city.nbRides = scanner.nextInt();
        city.rideBonus = scanner.nextInt();
        city.steps = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < city.nbRides; i++) {
            Ride ride = new Ride();
            ride.rowStart = scanner.nextInt();
            ride.columnStart = scanner.nextInt();
            ride.rowEnd = scanner.nextInt();
            ride.columnEnd = scanner.nextInt();
            ride.earlestStart = scanner.nextInt();
            ride.latestStart = scanner.nextInt();
            city.rides.add(ride);
        }

        // TODO Resolve
    }
}
