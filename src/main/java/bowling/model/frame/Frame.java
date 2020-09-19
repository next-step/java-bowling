package bowling.model.frame;

import bowling.model.Score;
import bowling.model.State;
import bowling.model.delivery.Delivery;
import bowling.model.delivery.DeliveryEntry;

import java.util.Objects;
import java.util.stream.Stream;

public abstract class Frame {

    DeliveryEntry deliveryEntry;

    Frame next;

    Frame(DeliveryEntry deliveryEntry){
        this.deliveryEntry = deliveryEntry;
    }

    public abstract Frame roll(int fallenPins);

    public boolean isEnd() {
        return deliveryEntry.isEnd();
    }

    public Stream<Delivery> getDeliveries() {
        return deliveryEntry.getDeliveries();
    }

    public abstract Score getScore();

    public State getState() {
        return deliveryEntry.getState();
    }

    public void calculateAdditionalScore(Score beforeScore) {
        getDeliveries().forEach(delivery -> {
            beforeScore.addScore(delivery.getFallenPins());
        });

        if (!beforeScore.isEndCalculate() && canCalculateBonus()) {
            next.calculateAdditionalScore(beforeScore);
        }
    }

    int getTotalFallenPins() {
        return deliveryEntry.getTotalFallenPins();
    }

    boolean canCalculateBonus() {
        return Objects.nonNull(next);
    }

}
