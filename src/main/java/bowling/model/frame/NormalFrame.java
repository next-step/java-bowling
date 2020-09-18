package bowling.model.frame;

import bowling.model.delivery.Delivery;
import bowling.model.delivery.NormalDeliveryEntry;

import java.util.Objects;
import java.util.stream.Stream;

public class NormalFrame implements Frame {

    private NormalDeliveryEntry normalDeliveryEntry;

    NormalFrame(NormalDeliveryEntry normalDeliveryEntry) {
        this.normalDeliveryEntry = normalDeliveryEntry;
    }

    public static Frame firstRoll(int fallenPins) {
        NormalDeliveryEntry normalDeliveryEntry = NormalDeliveryEntry.of(fallenPins);
        return new NormalFrame(normalDeliveryEntry);
    }

    @Override
    public Frame roll(int fallenPins) {
        normalDeliveryEntry.roll(fallenPins);
        return new NormalFrame(normalDeliveryEntry);
    }

    @Override
    public boolean isEnd() {
        return normalDeliveryEntry.isEnd();
    }

    @Override
    public Stream<Delivery> getDeliveries() {
        return normalDeliveryEntry.getDeliveries();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(normalDeliveryEntry, that.normalDeliveryEntry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalDeliveryEntry);
    }

}

