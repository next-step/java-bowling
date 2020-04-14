package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import static bowling.Constants.*;

public class NormalFrame implements Frame {

    private static final int MAX_NORMAL_FRAME_COUNT = 9;
    public static final String OVER_NORMAL_FRAME_NO_ERROR = "일반 Frame은 최대 9개까지만 생성할 수 있습니다.";
    private int frameNumber;
    private int playCount;
    private int firstFelledPin;         // TODO: State 내에서 관리해보자
    private int secondFelledPin;
    private State state;

    public NormalFrame(int frameNumber, State state) {
        assertFrameNo(frameNumber);
        this.frameNumber = frameNumber;
        this.playCount = ZERO;
        this.firstFelledPin = ZERO;
        this.secondFelledPin = ZERO;
        this.state = state;
    }

    public static Frame create(int frameNumber) {
        return new NormalFrame(frameNumber, new Ready());
    }

    public void play(int felledPin) {
        assertFelledPin(felledPin);

        if(playCount == 0) {
            firstFelledPin = felledPin;
        }
        else {
            secondFelledPin = felledPin;
        }

        playCount++;
    }

    @Override
    public Frame getNext() {
        if(frameNumber == MAX_NORMAL_FRAME_COUNT) {
            return FinalFrame.create();
        }
        return NormalFrame.create(frameNumber + 1);
    }

    @Override
    public State getStatus() {
        return state;
    }

    @Override
    public int getFrameNumber() {
        return frameNumber;
    }

    private void assertFelledPin(int felledPin) {
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

    private void assertFrameNo(int frameNumber) {
        if(frameNumber > MAX_NORMAL_FRAME_COUNT) {
            throw new IllegalArgumentException(OVER_NORMAL_FRAME_NO_ERROR);
        }
    }
}
