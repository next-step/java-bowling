package bowling.domain.state;

import bowling.domain.PinCount;

public abstract class Finished implements State {

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public State next(PinCount pinCount) {
        throw new IllegalStateException("완료된 상태에서 다음 상태를 생성할수 없습니다.");
    }
}
