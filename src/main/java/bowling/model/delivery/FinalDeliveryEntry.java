package bowling.model.delivery;

import bowling.model.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class FinalDeliveryEntry implements DeliveryEntry {
    private final static int DEFAULT_COUNT = 2;

    private List<Delivery> deliveries;
    private boolean canBonusDelivery;

    public FinalDeliveryEntry(int firstFallenPins) {
        deliveries = new ArrayList<>(Arrays.asList(playFirstDelivery(firstFallenPins)));
    }

    @Override
    public void roll(int fallenPins) {
        Delivery delivery = playDelivery(fallenPins);
        if (!canBonusDelivery) {
            verifyCanBonusDelivery(delivery);
        }

        deliveries.add(delivery);

    }

    private Delivery playFirstDelivery(int fallenPins) {
        Delivery firstDelivery = Delivery.of(fallenPins);
        if (firstDelivery.getState() == State.STRIKE) {
            canBonusDelivery = true;
        }

        return firstDelivery;
    }

    private Delivery playDelivery(int fallenPins) {
        Delivery before = deliveries.get(deliveries.size() - 1);
        if (before.getState() == State.STRIKE || before.getState() == State.SPARE) {
            return Delivery.of(fallenPins);
        }

        return before.next(fallenPins);
    }

    private void verifyCanBonusDelivery(Delivery secondDelivery) {
        State state = secondDelivery.getState();
        if (state == State.STRIKE || state == State.SPARE) {
            canBonusDelivery = true;
        }
    }

    @Override
    public boolean isEnd() {
        return canBonusDelivery ? isEndWithBonusDelivery() : isEndDefaultDelivery();
    }

    private boolean isEndDefaultDelivery() {
        return deliveries.size() == DEFAULT_COUNT;
    }

    private boolean isEndWithBonusDelivery() {
        return deliveries.size() == DEFAULT_COUNT + 1;
    }

    @Override
    public Stream<Delivery> getDeliveries() {
        return deliveries.stream();
    }

    @Override
    public State getState() {
        return deliveries.get(deliveries.size() - 1).getState();
    }

    @Override
    public int getTotalFallenPins() {
        return deliveries.stream()
                .map(Delivery::getFallenPins)
                .reduce(0, (a, b) -> a + b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalDeliveryEntry that = (FinalDeliveryEntry) o;
        return canBonusDelivery == that.canBonusDelivery &&
                Objects.equals(deliveries, that.deliveries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveries, canBonusDelivery);
    }

}
