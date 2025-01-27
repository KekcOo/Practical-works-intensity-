package strategy.v2;

public class PostDelivery implements DeliveryStrategy{
    @Override
    public double calculateCost(double weight) {
        return weight * 5;
    }
}
