package bowling.domain;

import bowling.Constants;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME_NO = 10;
    public static final String OVER_FINAL_FRAME_NO_ERROR = "마지막 Frame 입니다.";
    public static int ZERO = 0;

    private int playCount;
    private int firstFalledPin;         // TODO: 좋지 않음
    private int secondFalledPin;
    private int thirdFalledPin;
    private State state;

    public FinalFrame() {
        this.playCount = ZERO;
        this.firstFalledPin = ZERO;
        this.secondFalledPin = ZERO;
        this.thirdFalledPin = ZERO;
    }

    public void play(int falledPin) {
        assertFalledPin(falledPin);
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
    public int getNo() {
        return FINAL_FRAME_NO;
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
        return playCount == 0;
    }

    private void assertFirstFalledPin(int falledPin) {
        if(falledPin > Constants.MAX_PIN_COUNT || falledPin < Constants.MIN_PIN_COUNT) {
            throw new IllegalArgumentException(Frame.WRONG_FALLED_PIN);
        }
    }

    private void assertNotFirstFalledPin(int falledPin) {
        int canFalledPin = Constants.MAX_PIN_COUNT - firstFalledPin;
        if(falledPin > canFalledPin || falledPin < Constants.MIN_PIN_COUNT) {
            throw new IllegalArgumentException(Frame.WRONG_FALLED_PIN);
        }
    }
}
