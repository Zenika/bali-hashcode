package hashcode;

public class Simulation {

    private City city;

    public Simulation(City city) {
        this.city = city;
    }

    public void resolve() {

    }


    public boolean isEnded() {
        return true;
    }

    public static void simpleSolution(City city) {
        for (int i = 0; i < city.nbVehicules; i++) {
            city.vehicles[i].rides.add(city.rides[i]);
        }
    }
}
