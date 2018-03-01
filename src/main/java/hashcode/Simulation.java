package hashcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Simulation {

    private City city;

    public Simulation(City city) {
        this.city = city;
    }

    public void resolve() {

    }

    public static int getDistanceFromStart(Vehicle vehicule, Ride ride) {
        return  getDistance(vehicule.currentRow, ride.rowStart, vehicule.currentColumn, ride.columnStart);
    }

    public static int getDistanceFromEnd(Vehicle vehicule, Ride ride) {
        return getDistance(vehicule.currentRow, ride.rowEnd, vehicule.currentColumn, ride.columnEnd);
    }

    public static int getRideDistance(Ride ride) {
        return getDistance(ride.columnEnd, ride.columnStart, ride.rowEnd, ride.rowStart);
    }

    public static int getDistance(int a, int b, int c, int d) {
        return Math.abs(a-b) + Math.abs(c-d);
    }

    public boolean isEnded() {
        return true;
    }

    public static void simpleSolution(City city) {
        for (int i = 0; i < Math.min(city.nbVehicules, city.nbRides); i++) {
            city.vehicles.get(i).rides.add(city.rides.get(i));
        }
    }

    public static void simpleSolution2(City city) {
        System.err.print("max" + Math.max(city.nbVehicules, city.nbRides));
        for (int i = 0; i < city.nbRides; i++) {
            for (int j = 0;  i < city.nbRides && j < city.nbVehicules; i++, j++) {
                city.vehicles.get(j).rides.add(city.rides.get(i));
                city.vehicles.get(j).currentColumn = city.rides.get(i).columnEnd;
                city.vehicles.get(j).currentRow = city.rides.get(i).rowEnd;
                city.vehicles.get(j).currentRide = city.rides.get(i);
            }
        }
    }

    public static void simpleSolution3(City city) {
        List<Vehicle> availableVehicles = city.vehicles;
        for (int currentStep = 0; currentStep < city.steps && city.rides.size() > 0; currentStep++) {
            logError("step " + currentStep);
            for (Vehicle vehicle : city.vehicles) {
                //log("vehicle " + vehicle.id);
                if (vehicle.nextAvailableStep == currentStep) {
                    log("vehicle dispo " + vehicle.id);
                    Optional<Ride> ride2 = RideFinder.findClosestRide(city, currentStep, vehicle);
                    if (ride2.isPresent()) {
                        Ride ride = ride2.get();
                        log("ride associe " + ride.id + " avec vehicle " + vehicle.id);
                        ride.available = false;
                        vehicle.currentRide = ride;
                        vehicle.rides.add(ride);
                        vehicle.step = Simulation.getRideDistance(ride) + Simulation.getDistanceFromStart(vehicle, ride);
                        vehicle.nextAvailableStep = vehicle.step;
                        city.rides.remove(ride);
                    }
                }
            }
        }
    }

    public static int nbStepNecessary(Ride ride, Vehicle vehicle) {
        int distance = Simulation.getRideDistance(ride) + Simulation.getDistanceFromStart(vehicle, ride);
        //log("Distance " + distance);
        return distance;
    }
    
    public static void log(String msg) {
        //System.err.println(msg);
        
    }
    public static void logError(String msg) {
        System.err.println(msg);

    }
}
