package bowling.domain;

import bowling.Constants;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME_NO = 10;

    private int bowlCount;
    private int firstFalledPin;         // TODO: State 에석 관리해보자.
    private int secondFalledPin;
    private int thirdFalledPin;
    private State state;

    public FinalFrame() {
        this.bowlCount = Constants.ZERO;
        this.firstFalledPin = Constants.ZERO;
        this.secondFalledPin = Constants.ZERO;
        this.thirdFalledPin = Constants.ZERO;
    }

    public void play(int falledPin) {
        assertFalledPin(falledPin);

        if(bowlCount == 0) {
            firstFalledPin = falledPin;
        }
        else if(bowlCount == 1){
            secondFalledPin = falledPin;
        }
        else {
            thirdFalledPin = falledPin;
        }
        bowlCount++;
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

    private void assertFalledPin(int falledPin) {       // TODO: 3회 충족할 수 있도록 다시 구현 필요
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
}
