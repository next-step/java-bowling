package bowling.domain.frame;

import bowling.domain.exception.CannotBowlException;
import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.dto.FrameDTO;
import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame{
    private State state;
    private Frame nextFrame;

    private NormalFrame(int frameNo) {
        this.state = Ready.create();
        this.nextFrame = nextFrame(frameNo + 1);
    }

    public static NormalFrame init() {
        return new NormalFrame(FIRST_FRAME_NO);
    }

    private Frame nextFrame(int frameNo) {
        if(frameNo == LAST_FRAME_NO) {
            return FinalFrame.of();
        }
        return new NormalFrame(frameNo);
    }

    @Override
    public Frame bowl(int pitch) {
        if(state.isFinished()) {
            throw new CannotBowlException();
        }
        state = state.bowl(pitch);
        return this;
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    @Override
    public Frame getNext() {
        return nextFrame;
    }

    public FrameDTO exportFrameDTO() {
        List<StateDTO> stateDTOList = new ArrayList<>();
        stateDTOList.add(state.exportStateDTO());
        return new FrameDTO(stateDTOList);
    }
}
