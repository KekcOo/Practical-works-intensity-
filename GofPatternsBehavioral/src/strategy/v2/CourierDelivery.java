package strategy.v2;

public class CourierDelivery implements DeliveryStrategy {
    @Override
    public double calculateCost(double weight) {
        return weight * 10;
    }
}
