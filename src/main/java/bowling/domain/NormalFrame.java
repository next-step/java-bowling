package bowling.domain;

import java.util.Objects;

public class NormalFrame extends Frame {
    public static final int NORMAL_FRAME_MAX_LENGTH = 2;
    private static final int LAST_FRAME = 9;

    private Frame nextFrame;

    public NormalFrame() {
        this.states = new States();
        this.nextFrame = null;
    }

    @Override
    public void bowl(Pin fallenPin) {
        State state = State.bowl(this.states.getLastPin().getFallenPin(), fallenPin.getFallenPin(), this.states.getSize());
        setStates(state, fallenPin);
    }

    @Override
    public boolean isEndFrame() {
        return this.states.getSize() == NORMAL_FRAME_MAX_LENGTH || states.isLastStateStrike();
    }

    @Override
    public boolean isEndGame() {
        return false;
    }

    @Override
    public Frame getNextFrame(int frameNumber) {
        if (frameNumber == LAST_FRAME) {
            setNextFrame(new FinalFrame());
            return this.nextFrame;
        }
        setNextFrame(new NormalFrame());
        return this.nextFrame;
    }

    @Override
    public int getScore() {
        if (!isEndFrame()) {
            return WAITING_CALCULATION;
        }

        State lastState = states.getLastState();
        Score score = createScore(lastState);

        if (score.canCalculateScore()) {
            return score.getScore();
        }

        if (Objects.isNull(nextFrame)) {
            return WAITING_CALCULATION;
        }

        return nextFrame.calculateAdditionalScore(score);
    }

    @Override
    int calculateAdditionalScore(Score score) {
        score = states.calculateScore(score);

        if (score.canCalculateScore()) {
            return score.getScore();
        }

        if (Objects.isNull(nextFrame)) {
            return WAITING_CALCULATION;
        }

        return nextFrame.calculateAdditionalScore(score);
    }

    private Score createScore(State lastState) {
        if (lastState.isStrike()) {
            return Score.ofStrike();
        }

        if (lastState.isSpare()) {
            return Score.ofSpare();
        }

        return Score.ofMiss(states.getBeforeState().getFallenPins() + states.getLastPin().getFallenPin());
    }

    private void setStates(State state, Pin fallenPin) {
        this.states.add(state, fallenPin);
    }

    public void setNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    @Override
    public States getStates() {
        return states;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalFrame)) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(nextFrame, that.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nextFrame);
    }
}
