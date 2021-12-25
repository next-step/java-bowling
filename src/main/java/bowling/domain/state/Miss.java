package bowling.domain.state;

import bowling.domain.KnockedPinCount;

public class Miss extends AbstractFinished {
    public Miss(KnockedPinCount pinCount, KnockedPinCount newPinCount) {
        super(pinCount, newPinCount);
    }

    @Override
    public State bowl(int pinCount) {
        throw new IllegalArgumentException("Miss에선 추가 투구를 할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean hasBonus() {
        return false;
    }
}
