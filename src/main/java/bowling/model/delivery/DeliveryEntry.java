package bowling.model.delivery;

import bowling.model.State;

import java.util.stream.Stream;

public interface DeliveryEntry {

    void roll(int secondFallenPins);

    boolean isEnd();

    Stream<Delivery> getDeliveries();

    State getState();

    int getTotalFallenPins();

}
