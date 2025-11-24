public class Charger {

    private final int id;
    private final double rateKw;

    public Charger(int id, double rateKw) {
        if (rateKw <= 0.0) {
            throw new IllegalArgumentException("Charger rate must be > 0");
        }
        this.id = id;
        this.rateKw = rateKw;
    }

    public int getId() {
        return id;
    }

    public double getRateKw() {
        return rateKw;
    }

    @Override
    public String toString() {
        return "Charger{id=" + id + ", rateKw=" + rateKw + "}";
    }
}
