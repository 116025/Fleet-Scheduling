public class Truck {

    private final int id;
    private final double capacityKwh;
    private final double currentChargeKwh;

    public Truck(int id, double capacityKwh, double currentChargeKwh) {
        this.id = id;
        this.capacityKwh = capacityKwh;
        this.currentChargeKwh = currentChargeKwh;
    }

    public int getId() {
        return id;
    }

    public double getEnergyNeeded() {
        double needed = capacityKwh - currentChargeKwh;
        return Math.max(0.0, needed);
    }

    @Override
    public String toString() {
        return "Truck{id=" + id +
                ", capacityKwh=" + capacityKwh +
                ", currentChargeKwh=" + currentChargeKwh + "}";
    }
}
