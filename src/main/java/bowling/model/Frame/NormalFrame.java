package bowling.model.frame;

import bowling.CannotBowlException;
import bowling.model.Score;
import bowling.model.state.State;
import bowling.model.state.StateFactory;

public class NormalFrame implements Frame{
    private Frame next;
    private State state;
    private int no;

    public NormalFrame(int no) {
        this.state = StateFactory.ready();
        this.no = no;
    }

    @Override
    public Frame bowl(int countOfPin) throws CannotBowlException {
        state = state.bowl(countOfPin);
        if (state.isFinish()) {
            next = createFrame();
            return next;
        }

        return this;
    }

    @Override
    public int getNo() {
        return no;
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = state.calculateAdditionalScore(beforeScore);
        if (score.canCalculateScore()) {
            return score;
        }
        return next.calculateAdditionalScore(score);
    }

    private Frame createFrame() {
        if (no + 1 == FINAL_FRAME_NO) {
            return new FinalFrame();
        }

        return new NormalFrame(no + 1);
    }
}
