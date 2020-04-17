package bowling.domain;

import bowling.domain.State.Ready;
import bowling.domain.State.State;

public class NormalFrame implements Frame {
    private static final int MAX_FRAME_NUM = 10;
    private boolean bonusFlag = false;
    private State state;
    private int frameNum;

    public NormalFrame(int frameNum) {
        state = new Ready();
        this.frameNum = frameNum;
    }

    @Override
    public Frame bowl(int felledPins) {
        state = state.bowl(felledPins);
        return this;
    }

    public Frame create() {
        if (!state.isFinish()) {
            throw new IllegalArgumentException("끝나지 않은 상태에서는 새로운 프레임을 만들 수 없습니다.");
        }
        if (frameNum + 1 == MAX_FRAME_NUM) {
            return new FinalFrame(bonusFlag);
        }
        return new NormalFrame(frameNum + 1);
    }

    public void bonus() {
        bonusFlag = true;
    }

    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public int getFrameNum() {
        return frameNum;
    }

    @Override
    public String desc() {
        return state.getDesc();
    }
}
