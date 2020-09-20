package bowling.model.frame;

import bowling.model.Score;
import bowling.model.delivery.DeliveryEntry;
import bowling.model.delivery.NormalDeliveryEntry;

import java.util.Objects;

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
    public Score getScore() {
        int countOfBonusScore = getState().getCountOfBonusScore();
        Score score = Score.of(deliveryEntry.getTotalFallenPins(), countOfBonusScore);

        if (!score.isEndCalculate() && canCalculateBonus()) {
            next.calculateAdditionalScore(score);
        }

        return score;
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

