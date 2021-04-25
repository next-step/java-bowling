package bowling.domain.frame;

import bowling.domain.exception.CannotBowlException;
import bowling.domain.exception.NoRemainingFrameException;
import bowling.domain.state.FinalState;
import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.dto.FrameDTO;

public class Frame{
    public static final int FIRST_FRAME_NO = 1;
    public static final int LAST_FRAME_NO = 10;
    private final Frame nextFrame;
    private State state;


    private Frame(int frameNo) {
        this.state = frameNo == LAST_FRAME_NO ? FinalState.create() : Ready.create();
        this.nextFrame = nextFrame(frameNo + 1);
    }

    public static Frame of(int frameNo) {
        return new Frame(frameNo);
    }

    public static Frame init() {
        return new Frame(FIRST_FRAME_NO);
    }

    private Frame nextFrame(int frameNo) {
        if(frameNo > LAST_FRAME_NO) {
            return null;
        }
        return new Frame(frameNo);
    }

    public Frame bowl(int pitch) {
        if(state.isFinished()) {
            throw new CannotBowlException();
        }
        state = state.bowl(pitch);
        return this;
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public Frame getNext() {
        if(nextFrame == null){
            throw new NoRemainingFrameException();
        }
        return nextFrame;
    }

    public FrameDTO exportFrameDTO() {
        return new FrameDTO(state.exportStateDTO());
    }
}
