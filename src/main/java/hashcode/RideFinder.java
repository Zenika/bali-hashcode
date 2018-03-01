package hashcode;

import java.util.Optional;

public class RideFinder {

    public Optional<Ride> findClosestRide(City city, int step, Vehicle vehicle) {
        Ride best = null;
        for (Ride ride : city.rides) {
            if (ride.available || step < ride.earlestStart || step > ride.latestFinish) {
                continue;
            }

            best = ride;
            break;
        }

        return Optional.of(best);
    }
}
