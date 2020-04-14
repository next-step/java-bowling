package bowling.domain;

public class FinalFrame implements Frame {
    private static final int MAX_FRAME_NO = 1;
    public static final String OVER_FINAL_FRAME_NO_ERROR = "마지막 Frame 입니다.";
    public static int ZERO = 0;

    private int no;
    private int playCount;
    private int firstFalledPin;         // TODO: 좋지 않음
    private int secondFalledPin;
    private int thirdFalledPin;
    private State state;

    public FinalFrame(int no) {
        assertFrameNo(no);
        this.no = no;
        this.playCount = ZERO;
        this.firstFalledPin = ZERO;
        this.secondFalledPin = ZERO;
    }

    public Frame play(int falledPin) {
        assertFalledPin(falledPin);
        return null;
    }

    @Override
    public State getStatus() {
        return state;
    }

    @Override
    public Frame nextFrame() {
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
        if(falledPin > MAX_PIN_COUNT || falledPin < MIN_PIN_COUNT) {
            throw new IllegalArgumentException(Frame.WRONG_FALLED_PIN);
        }
    }

    private void assertNotFirstFalledPin(int falledPin) {
        int canFalledPin = MAX_PIN_COUNT - firstFalledPin;
        if(falledPin > canFalledPin || falledPin < MIN_PIN_COUNT) {
            throw new IllegalArgumentException(Frame.WRONG_FALLED_PIN);
        }
    }

    private void assertFrameNo(int no) {
        if(no > MAX_FRAME_NO) {
            throw new IllegalArgumentException(OVER_NORMAL_FRAME_NO_ERROR);
        }
    }
}
