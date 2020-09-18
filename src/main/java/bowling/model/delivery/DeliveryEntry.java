package bowling.model.delivery;

import java.util.stream.Stream;

public interface DeliveryEntry {

    void roll(int secondFallenPins);

    boolean isEnd();

    Stream<Delivery> getDeliveries();
}
