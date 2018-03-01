package hashcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.String.join;

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
        for (int v = 0; v < city.nbVehicules; v++)
            city.vehicles.add(new Vehicle());
        for(int i = 0; i < city.nbRides; i++) {
            Ride ride = new Ride();
            ride.id = i;
            ride.rowStart = scanner.nextInt();
            ride.columnStart = scanner.nextInt();
            ride.rowEnd = scanner.nextInt();
            ride.columnEnd = scanner.nextInt();
            ride.earlestStart = scanner.nextInt();
            ride.latestFinish = scanner.nextInt();
            city.rides.add(ride);
        }
        Simulation.simpleSolution2(city);
        // TODO Resolve

        printVehicules(city.vehicles);
    }

    private static void printVehicules(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            System.out.print(vehicle.rides.size());
            System.out.print(" ");
            System.out.println(vehicle.rides.stream().map(r -> Integer.toString(r.id)).collect(Collectors.joining((" "))));
        }

    }
}
