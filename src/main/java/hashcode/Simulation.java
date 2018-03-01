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
        for (int i = 0; i < Math.min(city.nbVehicules, city.nbRides); i++) {
            city.vehicles.get(i).rides.add(city.rides.get(i));
        }
    }

    public static void simpleSolution2(City city) {
        System.err.print("max" + Math.max(city.nbVehicules, city.nbRides));
        for (int i = 0; i < city.nbRides; i++) {
            for (int j = 0;  i < city.nbRides && j < city.nbVehicules; i++, j++) {
                city.vehicles.get(j).rides.add(city.rides.get(i));
                city.vehicles.get(j).currentColumn = city.rides.get(i).columnEnd;
                city.vehicles.get(j).currentRow = city.rides.get(i).rowEnd;
            }
        }
    }
}
