package bowling.domain.frame;

import bowling.domain.FrameScore;
import bowling.domain.Pins;
import bowling.domain.exception.CannotBowlException;
import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.dto.FrameDTO;
import bowling.dto.ScoreDTO;
import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame{
    private State state;
    private final Frame nextFrame;

    private NormalFrame(int frameNo) {
        this.state = Ready.create();
        this.nextFrame = nextFrame(frameNo + 1);
    }

    private Frame nextFrame(int frameNo) {
        if(frameNo == LAST_FRAME_NO) {
            return FinalFrame.of();
        }
        return new NormalFrame(frameNo);
    }

    public static NormalFrame init() {
        return new NormalFrame(FIRST_FRAME_NO);
    }

    @Override
    public void bowl(Pins pitch) {
        if(state.isFinished()) {
            throw new CannotBowlException();
        }
        state = state.stateAfterPitch(pitch);
    }

    @Override
    public FrameScore frameScore() {
        FrameScore frameScore = state.frameScore();
        if(frameScore.hasNoTryLeft()) {
            return frameScore;
        }
        return nextFrame.frameScoreWithBonus(frameScore);
    }

    @Override
    public FrameScore frameScoreWithBonus(FrameScore prevFrameScore) {
        FrameScore frameScore = state.frameScoreWithBonus(prevFrameScore);
        if(frameScore.hasNoTryLeft()) {
            return frameScore;
        }
        return nextFrame.frameScoreWithBonus(frameScore);
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    @Override
    public Frame next() {
        return nextFrame;
    }

    public FrameDTO exportFrameDTO() {
        List<StateDTO> stateDTOList = new ArrayList<>();
        stateDTOList.add(state.exportStateDTO());
        FrameScore frameScore = frameScore();
        ScoreDTO scoreDTO = new ScoreDTO(frameScore.score(), frameScore.isUnscoredScore());
        return new FrameDTO(stateDTOList, scoreDTO);
    }
}
