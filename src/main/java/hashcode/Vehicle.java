package hashcode;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    List<Ride> rides = new ArrayList<>();
    int currentRow;
    int currentColumn;
    Ride currentRide;

    boolean couldBeOnTime(int currentStep, Ride ride) {
        return (Simulation.getDistanceFromEnd(this, ride) > ride.latestFinish - Math.max(currentStep, ride.earlestStart));
    }
}
