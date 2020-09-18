package bowling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FinalDeliveryEntry {
    private final static int DEFAULT_COUNT = 2;

    private List<Delivery> deliveries;
    private boolean canBonusDelivery;

    public FinalDeliveryEntry(int firstFallenPins) {
        deliveries = new ArrayList<>();
        deliveries.add(playFirstDelivery(firstFallenPins));
    }

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

    public boolean isEnd() {
        return canBonusDelivery ? isEndBonusDelivery() : isEndNotBonusDelivery();
    }

    private boolean isEndNotBonusDelivery() {
        return deliveries.size() == DEFAULT_COUNT;
    }

    private boolean isEndBonusDelivery() {
        return deliveries.size() == DEFAULT_COUNT + 1;
    }

    public Stream<Delivery> getDeliveries() {
        return deliveries.stream();
    }
}
