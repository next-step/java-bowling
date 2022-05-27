package bowling.bowl;

import bowling.pin.Pins;

public abstract class Ended implements Bowl{
    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public Bowl pitch(Pins pins) {
        throw new IllegalArgumentException("더이상 투구할 수 없는 상태입니다.");
    }
}
