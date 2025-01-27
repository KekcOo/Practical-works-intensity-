package strategy.v2;

public class RunnerDelivery {
    public static void main(String[] args) {
        DeliveryService service = new DeliveryService(new CourierDelivery());
        System.out.println("Courier delivery: " + service.getDeliveryCost(5) );

        service.setStrategy(new PostDelivery());
        System.out.println("Post delivery: " + service.getDeliveryCost(5));
        service.setStrategy(new Pickup());
        System.out.println("Pickup delivery: " + service.getDeliveryCost(5));

    }
}
