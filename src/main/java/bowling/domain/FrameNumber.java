package bowling.domain;

public enum FrameNumber {
    FIRST,
    SECOND,
    THIRD,
    FOURTH,
    FIFTH,
    SIXTH,
    SEVENTH,
    EIGHTH,
    NINTH,
    LAST,
    FRAME_OVER;

    public static final int LAST_FRAME_NUMBER = 10;
    public static final int FRAME_NUMBER_INCREASE_UNIT = 1;

    public boolean isOver() {
        return this.equals(FRAME_OVER);
    }

    public FrameNumber next() {
        validateOverFrameNumber();
        return values()[this.ordinal() + FRAME_NUMBER_INCREASE_UNIT];
    }

    public int getNumber() {
        validateOverFrameNumber();
        return this.ordinal() + 1;
    }

    private void validateOverFrameNumber() {
        if (isOver()) {
            throw new IllegalStateException("프레임 최대 값을 초과했습니다.");
        }
    }
}
