package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.List;

public class NormalFrame implements Frame {

    private static final int NO_SCORE = -1;
    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 9;

    private State state = new Ready();
    private Score score = state.getScore();
    private final int number;
    private Frame nextFrame;

    private NormalFrame(final int number) {

        checkSize(number);
        this.number = number;
    }

    public static NormalFrame ready() {

        return new NormalFrame(MIN_FRAME_NUMBER);
    }

    public static NormalFrame next(final int number) {

        return new NormalFrame(number);
    }

    private void checkSize(final int number) {

        if (number < MIN_FRAME_NUMBER || number > MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException(MIN_FRAME_NUMBER + "~" + MAX_FRAME_NUMBER + " 사이의 숫자만 가능합니다.");
        }
    }

    private void validateNextFrame() {

        if (nextFrame == null) {
            throw new IllegalArgumentException("점수를 계산할 수 없습니다.");
        }
    }

    @Override
    public boolean isLastFrame() {

        return false;
    }

    @Override
    public boolean canBowl() {

        return !state.isFinished();
    }

    @Override
    public void bowl(final int number) {

        state = state.bowl(new Pin(number));

        if (state.isFinished()) {
            score = state.getScore();
            nextFrame = nextFrame();
        }
    }

    @Override
    public Frame nextFrame() {

        if (number == MAX_FRAME_NUMBER) {
            nextFrame = LastFrame.ready();
            return nextFrame;
        }

        nextFrame = new NormalFrame(number + 1);
        return nextFrame;
    }

    @Override
    public int getIntScore() {

        if (score.canCalculateScore()) {
            return score.getScore();
        }

        try {
            validateNextFrame();
            score = nextFrame.calculateAdditionalScore(score);
            return score.getScore();
        } catch (IllegalArgumentException e) {
            return NO_SCORE;
        }
    }

    @Override
    public Score calculateAdditionalScore(final Score beforeScore) {

       final Score score = state.calculateAdditionalScore(beforeScore);

        if (!score.canCalculateScore()) {
            validateNextFrame();
            return nextFrame.calculateAdditionalScore(score);
        }

        return score;
    }

    @Override
    public State getState() {

        return state;
    }

    @Override
    public List<State> getStates() {

        return List.of(state);
    }

    @Override
    public boolean isFinished() {

        return !canBowl();
    }

    @Override
    public Frame getNextFrame() {
        return nextFrame;
    }
}
