package hashcode;

import javax.swing.text.html.Option;
import java.util.*;

public class RideFinder {

    public static boolean isRideValidForVehicle(Ride ride, Vehicle vehicle, int step) {
        int rideDistance = Simulation.nbStepNecessary(ride, vehicle);
        if (step > ride.latestFinish || step + rideDistance > ride.latestFinish) {
            return false;
        }

        return true;
    }

    public static void removeSkippedRides(City city, int step) {
        for(Iterator<Ride> itr = city.rides.iterator() ; itr.hasNext() ;) {
            Ride ride = itr.next();
            if (step > ride.latestFinish) {
                itr.remove();
            }
        }
    }

    public static Optional<Ride> findHighBonusRide(List<Ride> rides, int step, Vehicle vehicle) {
        int bestRideIndex = -1;
        Ride bestRide = null;

        for(int i = 0 ; i < rides.size() ; i++) {
            Ride ride = rides.get(i);
            if (!isRideValidForVehicle(ride, vehicle, step)) {
                continue;
            }

            int distanceFromStart = Simulation.getDistanceFromStart(vehicle, ride);
            int startStep = step + distanceFromStart;
            if(startStep < ride.earlestStart) {
                continue;
            }

            if(startStep == ride.earlestStart) {
                bestRideIndex = i;
                bestRide = ride;
                break;
            }
        }

        if(bestRide != null) {
            rides.remove(bestRideIndex);
        }


        return Optional.ofNullable(bestRide);
    }

    public static Optional<Ride> findClosestRide(List<Ride> rides, int step, Vehicle vehicle) {
        if (rides.isEmpty()) {
            return Optional.empty();
        }

        int bestDistanceFromStart = Integer.MAX_VALUE;
        int bestRideIndex = Integer.MAX_VALUE;
        Ride bestRide = null;

        for(int i = 0 ; i < rides.size() ; i++) {
            Ride ride = rides.get(i);
            if (!isRideValidForVehicle(ride, vehicle, step)) {
                continue;
            }

            int distanceFromStart = Simulation.getDistanceFromStart(vehicle, ride);
            int startStep = step + distanceFromStart;
            if(startStep < ride.earlestStart) {
                continue;
            }

            if(distanceFromStart < bestDistanceFromStart) {
                bestDistanceFromStart = distanceFromStart;
                bestRideIndex = i;
                bestRide = ride;

                if(distanceFromStart == 0) {
                    break;
                }
            }
        }

        if(bestRide != null) {
            rides.remove(bestRideIndex);
        }


        return Optional.ofNullable(bestRide);
    }
}
