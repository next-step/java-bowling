package bowling.model.frame;

import bowling.model.delivery.Delivery;

import java.util.stream.Stream;

public interface Frame {

    Frame roll(int fallenPins);

    boolean isEnd();

    Stream<Delivery> getDeliveries();

}
