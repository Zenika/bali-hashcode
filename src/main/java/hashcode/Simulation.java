package hashcode;

import java.util.ArrayList;
import java.util.Iterator;
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
        List<Vehicle>[] availableVehicles = new List[city.steps];
        for(int i = 0 ; i < availableVehicles.length; i++) {
            availableVehicles[i] = new ArrayList<>(city.vehicles.size());
        }

        availableVehicles[0].addAll(city.vehicles);

        int initialRides = city.rides.size();
        logError("Nb de vehicles total " + city.nbVehicules + ", nb rides total " + initialRides + " nb steps " + city.steps);
        for (int currentStep = 0; currentStep < city.steps && city.rides.size() > 0; currentStep++) {
            if(currentStep % 1000 == 0) {
                System.err.println("step " + currentStep);
            }

            RideFinder.removeSkippedRides(city, currentStep);
            int max = 0;

            for (Iterator<Vehicle> itr = availableVehicles[currentStep].iterator() ; itr.hasNext();) {
                Vehicle vehicle = itr.next();

                if(max > 200) {
                    //break;
                }
                max++;

                //Optional<Ride> ride2 = RideFinder.findHighBonusRide(city.rides, currentStep, vehicle);
                Optional<Ride> ride2 = Optional.empty();

                if(!ride2.isPresent()) {
                    ride2 = RideFinder.findClosestRide(city.rides, currentStep, vehicle);
                }

                if (ride2.isPresent()) {
                    Ride ride = ride2.get();
                    ride.available = false;
                    vehicle.currentRide = ride;
                    vehicle.rides.add(ride);
                    int rideDistance = Simulation.getRideDistance(ride);
                    int distanceFromStart = Simulation.getDistanceFromStart(vehicle, ride);
                    vehicle.step = currentStep + rideDistance + distanceFromStart;

                    if(vehicle.step == currentStep) {
                        throw new RuntimeException("ERROR");
                    }
                    if(vehicle.step < availableVehicles.length - 1) {
                        availableVehicles[vehicle.step].add(vehicle);
                    }
                    vehicle.nextAvailableStep = vehicle.step;
                    city.rides.remove(ride);
                } else if(currentStep < city.steps - 10) {
                    availableVehicles[currentStep+10].add(vehicle);
                }
            }
        }
        logError("Nb de rides fait " + (initialRides - city.rides.size()) + " / " + initialRides);
    }

    public static int nbStepNecessary(Ride ride, Vehicle vehicle) {
        int distance = Simulation.getRideDistance(ride) + Simulation.getDistanceFromStart(vehicle, ride);
        //log("Distance " + distance);
        return distance;
    }
    
    public static void log(String msg) {
       // System.err.println(msg);
        
    }
    public static void logError(String msg) {
        //System.err.println(msg);
    }
}
