import java.util.*;

public class FleetScheduler {

    public  Map<Integer, List<Integer>> scheduleTrucks(
            List<Truck> trucks,
            List<Charger> chargers,
            double totalHours
    ) {
        Map<Integer, List<Integer>> schedule = new HashMap<>();

        if (chargers == null || chargers.isEmpty() || totalHours <= 0.0) {
            return schedule;
        }

        for (Charger c : chargers) {
            schedule.put(c.getId(), new ArrayList<>());
        }

        List<Truck> trucksNeedingCharge = new ArrayList<>();
        for (Truck t : trucks) {
            if (t.getEnergyNeeded() > 0.0) {
                trucksNeedingCharge.add(t);
            }
        }

        class TruckWithBestTime {
            final Truck truck;
            final double bestTime;

            TruckWithBestTime(Truck truck, double bestTime) {
                this.truck = truck;
                this.bestTime = bestTime;
            }
        }

        List<TruckWithBestTime> truckInfos = new ArrayList<>();

        for (Truck t : trucksNeedingCharge) {
            double energyNeeded = t.getEnergyNeeded();
            double minTime = Double.POSITIVE_INFINITY;

            for (Charger c : chargers) {
                double time = energyNeeded / c.getRateKw();
                minTime = Math.min(minTime, time);
            }

            if (minTime <= totalHours) {
                truckInfos.add(new TruckWithBestTime(t, minTime));
            }
        }

        truckInfos.sort(Comparator.comparingDouble(t -> t.bestTime));

        double[] usedTime = new double[chargers.size()];
        final double EPS = 1e-9;

        for (TruckWithBestTime info : truckInfos) {
            Truck t = info.truck;
            double energyNeeded = t.getEnergyNeeded();

            int bestChargerIndex = -1;
            double bestFinishTime = Double.POSITIVE_INFINITY;

            for (int i = 0; i < chargers.size(); i++) {
                Charger c = chargers.get(i);
                double finishTime = usedTime[i] + (energyNeeded / c.getRateKw());

                if (finishTime <= totalHours + EPS && finishTime < bestFinishTime) {
                    bestFinishTime = finishTime;
                    bestChargerIndex = i;
                }
            }

            if (bestChargerIndex != -1) {
                Charger chosen = chargers.get(bestChargerIndex);
                schedule.get(chosen.getId()).add(t.getId());
                usedTime[bestChargerIndex] = bestFinishTime;
            }
        }

        return schedule;
    }

    public  void printSchedule(Map<Integer, List<Integer>> schedule) {
        for (Map.Entry<Integer, List<Integer>> entry : schedule.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }


}
