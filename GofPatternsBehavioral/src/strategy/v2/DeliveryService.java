package strategy.v2;

public class DeliveryService {
    private DeliveryStrategy strategy;

    public DeliveryService(DeliveryStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(DeliveryStrategy strategy) {
        this.strategy = strategy;
    }

    public double getDeliveryCost(double weight) {
        return strategy.calculateCost(weight);
    }
}
