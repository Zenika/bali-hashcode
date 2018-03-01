package hashcode;

public class Simulation {

    private City city;

    public Simulation(City city) {
        this.city = city;
    }

    public void resolve() {

    }


    public static int getDistanceFromStart(Vehicle vehicule, Ride ride) {
        return Math.abs(vehicule.currentRow - ride.rowStart) + Math.abs(vehicule.currentColumn - ride.columnStart);
    }

    public static int getDistanceFromEnd(Vehicle vehicule, Ride ride) {
        return Math.abs(vehicule.currentRow - ride.rowEnd) + Math.abs(vehicule.currentColumn - ride.columnEnd);
    }

    public boolean isEnded() {
        return true;
    }

    public static void simpleSolution(City city) {
        for (int i = 0; i < city.nbVehicules; i++) {
            city.vehicles.get(i).rides.add(city.rides.get(i));
        }
    }
}
