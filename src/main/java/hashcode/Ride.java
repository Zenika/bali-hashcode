package hashcode;

import javafx.util.Pair;

public class Ride {
    int id;
    int rowStart;
    int columnStart;
    int rowEnd;
    int columnEnd;
    int earlestStart;
    int latestFinish;
    boolean available = true;

    public void findBestVehicle(City city) {
        Vehicle bestV = city.vehicles.get(0);
        for (Vehicle v : city.vehicles) {
            bestV = mini(bestV, v);
        }
        bestV.rides.add(this);
    }

    private Vehicle mini(Vehicle v1, Vehicle v2) {
        return score(v1) > score(v2) ? v2 : v1;
    }

    public int score(Vehicle v) {
        // TODO check if it can arrive on time
        int available = v.timePassed() - earlestStart;
        Pair<Integer, Integer> lastPos = v.rides.isEmpty() ? new Pair<>(0,0) :
                new Pair<>(v.rides.get(v.rides.size()-1).columnEnd, v.rides.get(v.rides.size()-1).rowEnd);
        int res = Math.abs(Simulation.getDistance(lastPos.getKey(),columnStart,lastPos.getValue(),rowStart) + available);
        return res > 0 ? Integer.MAX_VALUE : res;
    }

}
