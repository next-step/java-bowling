package bowling.domain.state;

import bowling.domain.pin.FallenPin;

public abstract class Finished implements FrameState {

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public FrameState bowl(FallenPin fallenPin) {
        throw new UnsupportedOperationException("끝난 상태에서는 더이상 투구할 수 없습니다.");
    }

    @Override
    public boolean isReady() {
        return false;
    }
}
