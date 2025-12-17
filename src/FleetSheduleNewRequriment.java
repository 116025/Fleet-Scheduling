import java.util.List;
import java.util.Map;

// Time-Sliced charging dvide the charging window into small time slots
// Cost-aware perioritization charging duing off peak cheapr time windows
// Greedy but felxible scheduling
import java.util.List;
import java.util.Map;

// Time-Sliced charging dvide the charging window into small time slots
// Cost-aware perioritization charging duing off peak cheapr time windows
// Greedy but felxible scheduling
import java.util.*;
public class FleetSheduleNewRequriment {
    private static final double SLOT_HOURS = 0.5;
    private static final double CRITICAL_REMAINING_KWH = 10.0;

    public Map<Integer, List<Integer>> scheduleCharging(
            List<Truck> trucks,
            List<Charger> chargers,
            List<Boolean> timeSlots
    ) {
        Map<Integer, List<Integer>> schedule = new HashMap<>();

        for (Charger c : chargers) {
            schedule.put(c.getId(), new ArrayList<>());
        }

        Map<Integer, Double> remainingEnergy = new HashMap<>();
        for (Truck t : trucks) {
            remainingEnergy.put(t.getId(), t.getEnergyNeeded());
        }

        for (boolean isCheapSlot : timeSlots) {

            List<Truck> candidates = new ArrayList<>();
            for (Truck t : trucks) {
                double remaining = remainingEnergy.get(t.getId());
                if (remaining > 0 && (isCheapSlot || remaining > CRITICAL_REMAINING_KWH)) {
                    candidates.add(t);
                }
            }

            candidates.sort((a, b) ->
                    Double.compare(
                            remainingEnergy.get(b.getId()),
                            remainingEnergy.get(a.getId())
                    )
            );

            Iterator<Truck> iterator = candidates.iterator();

            for (Charger charger : chargers) {
                if (!iterator.hasNext()) {
                    break;
                }

                Truck truck = iterator.next();

                double deliverable = charger.getRateKw() * SLOT_HOURS;
                double remaining = remainingEnergy.get(truck.getId());
                double delivered = Math.min(deliverable, remaining);

                remainingEnergy.put(truck.getId(), remaining - delivered);
                schedule.get(charger.getId()).add(truck.getId());
            }
        }

        return schedule;
    }

    public static void main(String[] args) {

        List<Truck> trucks = Arrays.asList(
                new Truck(1, 100, 20),
                new Truck(2, 80, 30),
                new Truck(3, 60, 10),
                new Truck(4, 40, 0)
        );

        List<Charger> chargers = Arrays.asList(
                new Charger(1, 50),
                new Charger(2, 30)
        );

        List<Boolean> timeSlots = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            timeSlots.add(true);
        }
        for (int i = 0; i < 4; i++) {
            timeSlots.add(false);
        }

        FleetSheduleNewRequriment scheduler = new FleetSheduleNewRequriment();
        Map<Integer, List<Integer>> schedule =
                scheduler.scheduleCharging(trucks, chargers, timeSlots);

        schedule.forEach((chargerId, truckIds) ->
                System.out.println("Charger " + chargerId + " -> " + truckIds)
        );
    }
}

