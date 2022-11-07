package bowling.domain.status;

import bowling.domain.Pin;

public class Spare extends Status {

    public Spare(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Status bowl(Pin pin) {
        throw new UnsupportedOperationException("더 이상 공을 던질 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }


}
