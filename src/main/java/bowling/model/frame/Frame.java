package bowling.model.frame;

import bowling.model.delivery.Delivery;
import bowling.model.delivery.DeliveryEntry;

import java.util.stream.Stream;

public abstract class Frame {

    DeliveryEntry deliveryEntry;

    Frame(DeliveryEntry deliveryEntry){
        this.deliveryEntry = deliveryEntry;
    }

    public abstract Frame roll(int fallenPins);

    public boolean isEnd() {
        return deliveryEntry.isEnd();
    }

    public Stream<Delivery> getDeliveries() {
        return deliveryEntry.getDeliveries();
    }

}
