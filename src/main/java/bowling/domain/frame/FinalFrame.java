package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import static bowling.Constants.*;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME_NO = 10;
    private State state;

    public FinalFrame(State state) {
        this.state = state;
    }

    public static Frame create() {
        return new FinalFrame(new Ready());
    }

    public void play(int felledPin) {
        assertFelledPin(felledPin);
        assertFelledPin(felledPin);
        state = state.play(felledPin);
    }

    @Override
    public State getStatus() {
        return state;
    }

    @Override
    public Frame getNext() {
        return null;
    }

    @Override
    public int getFrameNumber() {
        return FINAL_FRAME_NO;
    }

    private void assertFelledPin(int felledPin) {
        if(felledPin > MAX_FELLED_PIN_COUNT || felledPin < MIN_FELLED_PIN_COUNT) {
            throw new IllegalArgumentException(WRONG_FELLED_PIN);
        }
    }
}
