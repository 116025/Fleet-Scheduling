import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        FleetScheduler fleetScheduler = new FleetScheduler();

        List<Truck> trucks = Arrays.asList(
                new Truck(1, 60, 50),
                new Truck(2, 80, 10),
                new Truck(3, 60, 10),
                new Truck(4, 50, 0)
        );

        List<Charger> chargers = Arrays.asList(
                new Charger(1, 50),
                new Charger(2, 40)
        );

        Map<Integer, List<Integer>> schedule =
                fleetScheduler.scheduleTrucks(trucks, chargers, 2.0);

        fleetScheduler.printSchedule(schedule);
    }
}
