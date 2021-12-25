package bowling.domain.state;

import bowling.domain.KnockedPinCount;

public class FirstBowl implements State, Running {
    private final KnockedPinCount pinCount;

    public FirstBowl(int pinCount) {
        this.pinCount = new KnockedPinCount(pinCount);
    }

    @Override
    public State bowl(int count) {
        KnockedPinCount newPinCount = new KnockedPinCount(count);

        if (this.pinCount.sum(newPinCount).equals(KnockedPinCount.TEN_COUNT)) {
            return new Spare(this.pinCount, newPinCount);
        }

        return new Miss(this.pinCount, newPinCount);
    }

    @Override
    public int getFirst() {
        return pinCount.value();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean hasBonus() {
        return false;
    }
}
