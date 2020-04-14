package bowling.domain;

import bowling.Constants;

public class NormalFrame implements Frame {

    private static final int MAX_NORMAL_FRAME_COUNT = 9;
    public static final String OVER_NORMAL_FRAME_NO_ERROR = "일반 Frame은 최대 9개까지만 생성할 수 있습니다.";
    private int no;
    private int playCount;
    private int firstFalledPin;
    private int secondFalledPin;

    public NormalFrame(int no) {
        assertFrameNo(no);
        this.no = no;
        this.playCount = Constants.ZERO;
        this.firstFalledPin = Constants.ZERO;
        this.secondFalledPin = Constants.ZERO;
}

    public void play(int falledPin) {
        assertFalledPin(falledPin);

        playCount++;
    }

    @Override
    public Frame getNext() {
        if(no == MAX_NORMAL_FRAME_COUNT) {
            return new FinalFrame();
        }
        return new NormalFrame(no + 1);
    }

    @Override
    public State getStatus() {
        return null;
    }

    @Override
    public int getNo() {
        return no;
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

    private void assertFrameNo(int no) {
        if(no > MAX_NORMAL_FRAME_COUNT) {
            throw new IllegalArgumentException(OVER_NORMAL_FRAME_NO_ERROR);
        }
    }
}
