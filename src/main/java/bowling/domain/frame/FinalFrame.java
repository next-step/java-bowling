package bowling.domain.frame;

import bowling.domain.pins.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.Gutter;
import bowling.domain.state.Miss;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class FinalFrame implements Frame {

    private Frame next;
    private State state;

    private FinalFrame() {
        this.state = Ready.of();
    }

    public static FinalFrame of() {
        return new FinalFrame();
    }

    @Override
    public Frame bowl(Pins pins) {
        state = state.bowl(pins);
        if (isBonusFrame()) {
            next = BonusFrame.of(state);
            return next;
        }

        return this;
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public boolean isReady() {
        return state instanceof Ready;
    }

    @Override
    public String getState() {
        return state.toString();
    }

    @Override
    public Score getScore() {
        Score score = state.getScore();
        if (score.canCalculateScore()) {
            return score;
        }

        return next.calculateAdditionalScore(score);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        score = state.calculateAdditionalScore(score);
        if (score.canCalculateScore()) {
            return score;
        }
        return next.calculateAdditionalScore(score);
    }

    private boolean isMissOrGutter() {
        return state instanceof Miss || state instanceof Gutter;
    }

    private boolean isBonusFrame() {
        return isFinish() && !isMissOrGutter();
    }
}
