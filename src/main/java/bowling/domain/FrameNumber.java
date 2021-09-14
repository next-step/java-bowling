package bowling.domain;

public class FrameNumber {

    private static final int FIRST_FRAME_NUMBER = 1;
    private static final int LAST_FRAME_NUMBER = 10;
    private static final int GAP_BETWEEN_NUMBER_AND_INDEX = 1;

    private int number;

    public FrameNumber() {
        this.number = FIRST_FRAME_NUMBER;
    }

    public boolean isOver() {
        return this.number > LAST_FRAME_NUMBER;
    }

    public void increase() {
        if (isOver()) {
            throw new IllegalStateException("프레임 최대 값을 초과했습니다.");
        }
        this.number++;
    }

    public int getNumber() {
        return number;
    }

    public int frameIndex() {
        return number - GAP_BETWEEN_NUMBER_AND_INDEX;
    }
}
