package bowling.domain.state;

import bowling.domain.Pins;

public abstract class EndState implements FrameState {
    @Override
    public FrameState bowl(Pins hitPins) {
        throw new IllegalArgumentException("프레임의 투구가 완료된 상태에서 볼을 굴릴 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}