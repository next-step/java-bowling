package bowling.model.frame;

import bowling.model.delivery.Delivery;
import bowling.model.delivery.DeliveryEntry;
import bowling.model.delivery.FinalDeliveryEntry;

import java.util.Objects;
import java.util.stream.Stream;

public class FinalFrame extends Frame {

    FinalFrame(DeliveryEntry finalDeliveryEntry) {
        super(finalDeliveryEntry);
    }

    public static Frame of(int fallenPins) {
        FinalDeliveryEntry finalDeliveryEntry = new FinalDeliveryEntry(fallenPins);
        return new FinalFrame(finalDeliveryEntry);
    }

    @Override
    public Frame roll(int fallenPins) {
        deliveryEntry.roll(fallenPins);
        return new FinalFrame(deliveryEntry);
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
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(deliveryEntry, that.deliveryEntry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryEntry);
    }

}
