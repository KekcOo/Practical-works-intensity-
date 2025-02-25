package strategy.witchout;



public class DeliveryService { private String deliveryType;

    public DeliveryService(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public double getDeliveryCost(double weight) {
        switch (deliveryType) {
            case "Courier":
                return weight * 10;
            case "Post":
                return weight * 5;
            case "Pickup":
                return 0;
            default:
                throw new IllegalArgumentException("Unknown delivery type: " + deliveryType);
        }
    }




    public static void main(String[] args) {
        DeliveryService service = new DeliveryService("Courier");
        System.out.println("Курьер доставка: " + service.getDeliveryCost(5));

        service.setDeliveryType("Post");
        System.out.println("Почта доставка: " + service.getDeliveryCost(5));

        service.setDeliveryType("Pickup");
        System.out.println("Самовывоз доставка: " + service.getDeliveryCost(5));
    }
}
