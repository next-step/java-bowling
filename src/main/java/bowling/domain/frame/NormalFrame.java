package bowling.domain.frame;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.normal.Ready;
import bowling.exception.CannotCalculateException;

import java.util.Optional;

public class NormalFrame extends Frame {

    public NormalFrame(int no) {
        super(no, new Ready());
    }

    @Override
    public Score getScore() {
        return score.orElseGet(() -> {
            Score newScore = calculateScore();
            score = Optional.ofNullable(newScore);
            return newScore;
        });
    }

    private Score calculateScore() {
        Score score = getState().getScore();
        if (score.canCalculate()) {
            return score;
        }

        return getNext().calculateBonusScore(score);
    }

    @Override
    protected Score calculateBonusScore(Score before) {
        Score score = getState().calculateBonusScore(before);
        if (score.canCalculate()) {
            return score;
        }

        return Optional.ofNullable(getNext())
                .map(f -> f.calculateBonusScore(score))
                .orElseThrow(CannotCalculateException::new);
    }

    @Override
    public Frame bowl(PinCount pinCount) {
        state = state.next(pinCount);
        if (state.isFinish()) {
            next = createFrame();
            return next;
        }

        return this;
    }

    private Frame createFrame() {
        if (no + 1 == 10) {
            return new LastFrame();
        }

        return new NormalFrame(no + 1);
    }
}
