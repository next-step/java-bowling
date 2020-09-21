package bowling.model.frame;

import bowling.model.Score;
import bowling.model.delivery.DeliveryEntry;
import bowling.model.delivery.FinalDeliveryEntry;

import java.util.Objects;

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
    public Score getScore() {
        return Score.of(getTotalFallenPins(), 0);
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
