package bowling.domain.pin;

import bowling.domain.state.StateExpression;

public class Pins {

    private final PinCount hitCount;

    private Pins(final PinCount hitCount) {
        this.hitCount = hitCount;
    }

    public static Pins of(final int hitCount) {
        return new Pins(PinCount.of(hitCount));
    }

    public static Pins of(final PinCount hitCount) {
        return new Pins(hitCount);
    }

    public boolean isStrike() {
        return this.hitCount.isMaxCount();
    }

    public boolean isSpare(final Pins secondPins) {
        return totalPins(secondPins).isMaxCount();
    }

    public boolean isMiss(final Pins secondPins) {
        return totalPins(secondPins).isLessThanMaxCount();
    }

    private PinCount totalPins(final Pins secondPins) {
        return this.hitCount.increase(secondPins.hitCount);
    }

    public String getHitCount() {
        if (this.isGutter()) {
            return StateExpression.GUTTER;
        }
        return String.valueOf(hitCount.getCount());
    }

    private boolean isGutter() {
        return this.hitCount.isMinCount();
    }
}
