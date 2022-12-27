package bowling.domain.state;

import bowling.domain.Pin;

abstract public class Finished implements Status {

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Status bowl(Pin pin) {
        throw new IllegalStateException("더 이상 게임을 진행할 수 없습니다.");
    }

}
