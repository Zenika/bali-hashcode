package hashcode;

import javafx.util.Pair;

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

    public boolean addRide(Ride ride, City city) {
        if (this.rides.isEmpty()) {
            this.rides.add(ride);
            return true;
        }
        Ride lastRide = this.rides.get(rides.size()-1);
        Pair<Integer,Integer> lastPair = new Pair<>(lastRide.rowEnd,lastRide.columnEnd);
        if (dist(lastPair, new Pair<>(ride.rowStart, ride.columnStart)) <= city.steps-this.timePassed()) {
            this.rides.add(ride);
            return true;
        }
        return false;
    }

    public int timePassed() {
        if (rides.isEmpty()) return 0;

        int init = Simulation.getDistance(0,rides.get(0).columnStart,0, rides.get(0).rowStart);
        if (rides.size() == 1) return init;

        for (Ride r : rides) {
            init += Simulation.getDistance(r.columnEnd,r.columnStart,r.rowEnd,r.rowStart);
        }
        return init;
    }

    public int dist(Pair<Integer,Integer> start, Pair<Integer,Integer> end) {
        return Simulation.getDistance(start.getKey(), end.getKey(), start.getValue(), end.getValue());
    }
}
