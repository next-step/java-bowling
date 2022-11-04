package bowling.domain;

import bowling.domain.state.State;

import java.util.List;

public class NormalFrame extends Frame {
    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 9;

    public NormalFrame() {
        this(1);
    }

    public NormalFrame(int number) {
        checkSize(number);

        this.number = number;
    }

    private void checkSize(int number) {
        if (number < MIN_FRAME_NUMBER || number > MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException(MIN_FRAME_NUMBER + "~" + MAX_FRAME_NUMBER + " 사이의 숫자만 가능합니다.");
        }
    }

    public void bowl(int number) {
        state = state.bowl(Pin.of(number));

        if (state.isFinished()) {
            score = state.getScore();
            nextFrame = nextFrame();
        }
    }

    @Override
    public boolean canBowl() {
        return !state.isFinished();
    }

    @Override
    public Frame nextFrame() {
        if (number == MAX_FRAME_NUMBER) {
            nextFrame = new FinalFrame(number + 1);
            return nextFrame;
        }

        nextFrame = new NormalFrame(number + 1);
        return nextFrame;
    }

    @Override
    public boolean isLastFrame() {
        return false;
    }

    @Override
    public List<State> getStates() {
        return List.of(state);
    }
}
