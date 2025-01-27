package strategy.v2;

public class Pickup implements DeliveryStrategy{
    @Override
    public double calculateCost(double weight) {
        return 0;
    }
}
