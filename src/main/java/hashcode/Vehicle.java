package hashcode;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    List<Ride> rides = new ArrayList<>();
    int currentRow = 0;
    int currentColumn = 0;
    int nextAvailableStep = 0;
    Ride currentRide;
    int step = 0;

    boolean couldBeOnTime(int currentStep, Ride ride) {
        return (Simulation.getDistanceFromEnd(this, ride) > ride.latestFinish - Math.max(currentStep, ride.earlestStart));
    }
}
