package bowling.domain.state;

import bowling.domain.Pinfall;

public class FrameStateStrike implements FrameState{
    @Override
    public FrameState roll(Pinfall pinfall) {
        throw new IllegalArgumentException("공을 굴릴 수 없습니다");
    }

    @Override
    public boolean isRollable() {
        return false;
    }
}
