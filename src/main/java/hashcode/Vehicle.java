package hashcode;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    int id;
    List<Ride> rides = new ArrayList<>();
    int currentRow = 0;
    int currentColumn = 0;
    int nextAvailableStep = 0;
    Ride currentRide;
    int step = 0;

    public Vehicle(int v) {
        this.id = v;
    }

    boolean couldBeOnTime(int currentStep, Ride ride) {
        return (Simulation.getDistanceFromEnd(this, ride) > ride.latestFinish - Math.max(currentStep, ride.earlestStart));
    }
}
