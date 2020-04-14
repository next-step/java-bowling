package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import static bowling.Constants.*;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME_NO = 10;

    private int playCount;
    private int firstFelledPin;         // TODO: State 에석 관리해보자.
    private int secondFelledPin;
    private int thirdFelledPin;
    private State state;

    public FinalFrame(State state) {
        this.playCount = ZERO;
        this.firstFelledPin = ZERO;
        this.secondFelledPin = ZERO;
        this.thirdFelledPin = ZERO;
        this.state = state;
    }

    public static Frame create() {
        return new FinalFrame(new Ready());
    }

    public void play(int felledPin) {
        assertFelledPin(felledPin);

        if(playCount == 0) {
            firstFelledPin = felledPin;
        }
        else if(playCount == 1){
            secondFelledPin = felledPin;
        }
        else {
            thirdFelledPin = felledPin;
        }
        playCount++;
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

    private void assertFelledPin(int felledPin) {       // TODO: 3회 충족할 수 있도록 다시 구현 필요
        if(isFirstPlay()) {
            assertFirstFelledPin(felledPin);
        }
        else {
            assertNotFirstFelledPin(felledPin);
        }
    }

    private boolean isFirstPlay() {
        return playCount == 0;
    }

    private void assertFirstFelledPin(int felledPin) {
        if(felledPin > MAX_FELLED_PIN_COUNT || felledPin < MIN_FELLED_PIN_COUNT) {
            throw new IllegalArgumentException(WRONG_FELLED_PIN);
        }
    }

    private void assertNotFirstFelledPin(int felledPin) {
        int canFelledPin = MAX_FELLED_PIN_COUNT - firstFelledPin;
        if(felledPin > canFelledPin || felledPin < MIN_FELLED_PIN_COUNT) {
            throw new IllegalArgumentException(WRONG_FELLED_PIN);
        }
    }
}
