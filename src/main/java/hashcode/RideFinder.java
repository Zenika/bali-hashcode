package hashcode;

import hashcode.training.Solution;

import java.util.Optional;

public class RideFinder {

    public static Optional<Ride> findClosestRide(City city, int step, Vehicle vehicle) {
        Ride best = null;

        for (Ride ride : city.rides) {
            if (!ride.available || step > ride.latestFinish || step + Simulation.nbStepNecessary(ride, vehicle) > ride.latestFinish) {
                continue;
            }

            int distanceFromStart = Simulation.getDistanceFromStart(vehicle, ride);
            int startStep = step + distanceFromStart;
            if(startStep < ride.earlestStart) {
                continue;
            }
            Simulation.log("ok find closest");


            if(best == null) {
                best = ride;
            } else if(distanceFromStart < Simulation.getDistanceFromStart(vehicle, best)) {
                best = ride;
            }
        }

        return Optional.ofNullable(best);
    }
}
