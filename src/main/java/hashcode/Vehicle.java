package hashcode;

import java.util.List;

public class Vehicle {
    List<Ride> rides;
    int currentRow;
    int currentColumn;

    boolean couldBeOnTime(int currentStep, Ride ride) {
        return (Simulation.getDistanceFromEnd(this, ride) > ride.latestFinish - Math.max(currentStep, ride.earlestStart));
    }
}