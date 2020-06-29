package bowling.domain;

import java.util.Objects;

public class NormalFrame extends Frame {
    public static final int NORMAL_FRAME_MAX_LENGTH = 2;
    private static final int LAST_FRAME = 9;

    private Frame nextFrame;

    public NormalFrame() {
        this.pin = new Pin(Pin.MIN_PIN);
        this.states = new States();
        this.nextFrame = null;
    }

    @Override
    public void bowl(Pin fallenPin) {
        State state = State.bowl(this.pin.getFallenPin(), fallenPin.getFallenPin(), this.states.getSize());
        setStates(state, fallenPin);
        setPin(fallenPin);
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
        for (Pin pin : states.getPins()) {
            score = score.bowl(pin.getFallenPin());

            if (score.canCalculateScore()) {
                return score.getScore();
            }
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

        return Score.ofMiss(states.getBeforeState().getFallenPins() + pin.getFallenPin());
    }

    private void setStates(State state, Pin fallenPin) {
        this.states.add(state, fallenPin);
    }

    private void setPin(Pin fallenPin) {
        this.pin = fallenPin;
    }

    public void setNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    public Pin getPin() {
        return pin;
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
        return Objects.equals(pin, that.pin) &&
                Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin, states);
    }
}
