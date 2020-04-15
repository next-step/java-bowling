package bowling.domain.frame;

import bowling.domain.frame.state.FinalFrameStates;
import bowling.domain.frame.state.State;
import bowling.exception.BowlingException;

public class FinalFrame implements Frame {

    private static final String LAST_FRAME = "10번 이후의 프레임은 생성 불가";

    private final FinalFrameStates states;

    public FinalFrame() {
        this.states = FinalFrameStates.of();
    }

    public FinalFrame(FinalFrameStates states) {
        this.states = states;
    }

    @Override
    public boolean isFinish() {
        return states.isFinish();
    }

    @Override
    public Frame createNext() {
        throw new BowlingException(LAST_FRAME);
    }

    @Override
    public Frame bowl(int pinCount) {
        if (isFinish()) {
            throw new BowlingException(State.CANT_THROW_BALL);
        }

        FinalFrameStates bowl = states.bowl(pinCount);
        return new FinalFrame(bowl);
    }

    @Override
    public Frame getNext() {
        throw new BowlingException("마지막 프레임 입니다");
    }
}
