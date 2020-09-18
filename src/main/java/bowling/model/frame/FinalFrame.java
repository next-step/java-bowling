package bowling.model.frame;

import bowling.model.delivery.Delivery;
import bowling.model.delivery.FinalDeliveryEntry;

import java.util.Objects;
import java.util.stream.Stream;

public class FinalFrame implements Frame {

    private FinalDeliveryEntry finalDeliveryEntry;

    FinalFrame(FinalDeliveryEntry finalDeliveryEntry) {
        this.finalDeliveryEntry = finalDeliveryEntry;
    }

    public static Frame firstRoll(int fallenPins) {
        FinalDeliveryEntry finalDeliveryEntry = new FinalDeliveryEntry(fallenPins);
        return new FinalFrame(finalDeliveryEntry);

    }

    @Override
    public Frame roll(int fallenPins) {
        finalDeliveryEntry.roll(fallenPins);
        return new FinalFrame(finalDeliveryEntry);
    }

    @Override
    public boolean isEnd() {
        return finalDeliveryEntry.isEnd();
    }

    @Override
    public Stream<Delivery> getDeliveries() {
        return finalDeliveryEntry.getDeliveries();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(finalDeliveryEntry, that.finalDeliveryEntry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(finalDeliveryEntry);
    }

}
