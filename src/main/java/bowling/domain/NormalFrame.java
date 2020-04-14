package bowling.domain;

import bowling.Constants;

public class NormalFrame implements Frame {

    private static final int MAX_NORMAL_FRAME_COUNT = 9;
    public static final String OVER_NORMAL_FRAME_NO_ERROR = "일반 Frame은 최대 9개까지만 생성할 수 있습니다.";
    private int frameNumber;
    private int bowlCount;
    private int firstFalledPin;         // TODO: State 내에서 관리해보자
    private int secondFalledPin;
    private State state;

    public NormalFrame(int frameNumber, State state) {
        assertFrameNo(frameNumber);
        this.frameNumber = frameNumber;
        this.bowlCount = Constants.ZERO;
        this.firstFalledPin = Constants.ZERO;
        this.secondFalledPin = Constants.ZERO;
        this.state = state;
    }

    public static Frame create(int frameNumber) {
        return new NormalFrame(frameNumber, new Ready());
    }

    public void play(int falledPin) {
        assertFalledPin(falledPin);

        if(bowlCount == 0) {
            firstFalledPin = falledPin;
        }
        else {
            secondFalledPin = falledPin;
        }

        bowlCount++;
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

    private void assertFalledPin(int falledPin) {
        if(isFirstBowl()) {
            assertFirstFalledPin(falledPin);
        }
        else {
            assertNotFirstFalledPin(falledPin);
        }
    }

    private boolean isFirstBowl() {
        return bowlCount == 0;
    }

    private void assertFirstFalledPin(int falledPin) {
        if(falledPin > Constants.MAX_FALL_PIN_COUNT || falledPin < Constants.MIN_FALL_PIN_COUNT) {
            throw new IllegalArgumentException(Frame.WRONG_FALLED_PIN);
        }
    }

    private void assertNotFirstFalledPin(int falledPin) {
        int canFalledPin = Constants.MAX_FALL_PIN_COUNT - firstFalledPin;
        if(falledPin > canFalledPin || falledPin < Constants.MIN_FALL_PIN_COUNT) {
            throw new IllegalArgumentException(Frame.WRONG_FALLED_PIN);
        }
    }

    private void assertFrameNo(int frameNumber) {
        if(frameNumber > MAX_NORMAL_FRAME_COUNT) {
            throw new IllegalArgumentException(OVER_NORMAL_FRAME_NO_ERROR);
        }
    }
}
