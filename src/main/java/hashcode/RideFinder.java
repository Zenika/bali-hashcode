package hashcode;

import java.util.Optional;

public class RideFinder {

    public static Optional<Ride> findClosestRide(City city, int step, Vehicle vehicle) {
        if (city.rides.isEmpty()) {
            return Optional.empty();
        }
        Ride best = null;
        int distanceFromStartBest = Integer.MAX_VALUE;
        int startStepBest = Integer.MAX_VALUE;

        for (Ride ride : city.rides) {
            if (!ride.available || step > ride.latestFinish || step + Simulation.nbStepNecessary(ride, vehicle) > ride.latestFinish) {
                continue;
            }

            int distanceFromStart = Simulation.getDistanceFromStart(vehicle, ride);
            int startStep = step + distanceFromStart;
            if(startStep < ride.earlestStart) {
            //if(ride.earlestStart - startStep > 50) {
                continue;
            }

            if(best == null) {
                Simulation.log("best null ride " + ride.id + ", vehicle " + vehicle.id + " distance start " + distanceFromStart);
                best = ride;
                distanceFromStartBest = distanceFromStart;
                startStepBest = startStep;
                //bonusBest = ride.
            } else {
                if(distanceFromStart + startStep < distanceFromStartBest + startStepBest) {
                    Simulation.log("meilleur ride " + ride.id + ", vehicle " + vehicle.id + " distance start " + distanceFromStart + ", startstep " + startStep);

                    best = ride;
                    distanceFromStartBest = distanceFromStart;
                    startStepBest = startStep;
                    if (distanceFromStart == 0) {
                        Simulation.log("Meilleur ride distance 0");
                        return Optional.ofNullable(best);
                    }
                }
            }
        }
        if (best == null) {
            Simulation.log("Aucun ride dispo");
            return Optional.empty();
        }
        Simulation.log("Meilleur ride " + best.id + ", vehicle " + vehicle.id + " distance start " + distanceFromStartBest);
        return Optional.ofNullable(best);
    }
}
