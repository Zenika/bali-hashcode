package hashcode;

import java.util.Optional;

public class RideFinder {

    public static Optional<Ride> findClosestRide(City city, int step, Vehicle vehicle) {
        Ride best = null;
        for (Ride ride : city.rides) {
            if (ride.available || step + Simulation.nbStepNecessary(ride, vehicle) > city.steps) {
                continue;
            }

            best = ride;
            break;
        }

        return Optional.ofNullable(best);
    }
}
