package bowling.domain.state;

import bowling.domain.KnockedPinCount;

public class Spare extends AbstractFinished {
    public Spare(KnockedPinCount pinCount, KnockedPinCount newPinCount) {
        super(pinCount, newPinCount);
    }

    @Override
    public State bowl(int pinCount) {
        return Bonus.ofSpare(knockedPinCounts.knockOut(pinCount), this);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean hasBonus() {
        return true;
    }
}
