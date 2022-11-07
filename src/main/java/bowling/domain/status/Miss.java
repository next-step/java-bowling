package bowling.domain.status;

import bowling.domain.Pin;

public class Miss extends Status {

    private final Pin first;
    private final Pin second;

    public Miss(Pin first, Pin second) {
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
