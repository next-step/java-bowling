package bowling.domain;

import bowling.domain.State.LastFrameBowl;
import bowling.domain.State.State;
import bowling.exception.CannotCalculateException;

public class FinalFrame implements Frame {
    private static final int MAX_FRAME_NUM = 10;
    private State state;

    public FinalFrame(boolean bonusFlag) {
        state = new LastFrameBowl(bonusFlag);
    }

    @Override
    public Frame bowl(int felledPins) {
        state = state.bowl(felledPins);
        return this;
    }

    @Override
    public int getFrameNum() {
        return MAX_FRAME_NUM;
    }

    @Override
    public Score getScore() {
        return state.getScore();
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = state.calculateAdditionalScore(beforeScore);
        if (score.canCalculateScore()) {
            return score;
        }
        return score;
    }

    @Override
    public void addFrameResult(Board board) {
        board.add(getFrameResult());
    }

    public FrameResult getFrameResult() {
        if (!state.isFinish()) {
            return new FrameResult(state.getDesc());
        }

        try {
            return new FrameResult(state.getDesc(), getScore().getScore());
        } catch (CannotCalculateException e) {
            return new FrameResult(state.getDesc());
        }
    }
}
