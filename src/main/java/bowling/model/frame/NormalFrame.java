package bowling.model.frame;

import bowling.model.delivery.Delivery;
import bowling.model.delivery.DeliveryEntry;
import bowling.model.delivery.NormalDeliveryEntry;

import java.util.Objects;
import java.util.stream.Stream;

public class NormalFrame extends Frame {

    NormalFrame(DeliveryEntry normalDeliveryEntry) {
        super(normalDeliveryEntry);
    }

    public static Frame of(int fallenPins) {
        NormalDeliveryEntry normalDeliveryEntry = NormalDeliveryEntry.of(fallenPins);
        return new NormalFrame(normalDeliveryEntry);
    }

    @Override
    public Frame roll(int fallenPins) {
        deliveryEntry.roll(fallenPins);
        return new NormalFrame(deliveryEntry);
    }

    @Override
    public boolean isEnd() {
        return deliveryEntry.isEnd();
    }

    @Override
    public Stream<Delivery> getDeliveries() {
        return deliveryEntry.getDeliveries();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(deliveryEntry, that.deliveryEntry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryEntry);
    }

}

