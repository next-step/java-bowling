package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Objects;

public abstract class Frame {

    public static final int NO_SCORE = -1;

    protected int number;
    protected State state = new Ready();
    protected Frame nextFrame;
    protected Score score = state.getScore();

    protected Frame() {

    }

    public void bowl(int number) {
        state = state.bowl(Pin.of(number));

        if (state.isFinished()) {
            score = state.getScore();
            nextFrame = nextFrame();
        }
    }

    public boolean isFinished() {
        return !canBowl();
    }

    public abstract boolean canBowl();

    public abstract Frame nextFrame();

    public int number() {
        return number;
    }

    public abstract boolean isLastFrame();

    public int getIntScore() {
        if (score.canCalculateScore()) {
            return score.getScore();
        }

        validateNextFrame();

        score = nextFrame.calculateAdditionalScore(score);
        return score.getScore();
    }

    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = state.calculateAdditionalScore(beforeScore);

        if (!score.canCalculateScore()) {
            validateNextFrame();
            return nextFrame.calculateAdditionalScore(score);
        }

        return score;
    }

    private void validateNextFrame() {
        if (Objects.isNull(nextFrame)) {
            throw new UnsupportedOperationException("점수를 계산할 수 없습니다.");
        }
    }

    public State getState() {
        return state;
    }

    public Frame getNextFrame() {
        return nextFrame;
    }
}
