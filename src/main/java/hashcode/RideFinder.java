package hashcode;

import java.util.Optional;

public class RideFinder {

    public static Optional<Ride> findClosestRide(City city, int step, Vehicle vehicle) {
        Ride best = null;
        for (Ride ride : city.rides) {
            if (!ride.available || step > ride.latestFinish || step + Simulation.nbStepNecessary(ride, vehicle) > ride.latestFinish) {
                continue;
            }
            //Simulation.log("ok find closest");

            if(step + Simulation.getDistanceFromStart(vehicle, ride) < ride.earlestStart) {
                continue;
            }


            best = ride;
            break;
        }

        return Optional.ofNullable(best);
    }
}
