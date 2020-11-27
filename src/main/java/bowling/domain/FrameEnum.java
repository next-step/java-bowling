package bowling.domain;

public enum FrameEnum {
    STRIKE,
    SPARE,
    MISS,
    UNFINISHED;

    public static final int PIN_NUM = 10;
    public static final int ROLL_NUM_PER_FRAME = 2;

    static FrameEnum get(int rollIdxSize, int countOfPins) {
        if (rollIdxSize >= ROLL_NUM_PER_FRAME && countOfPins >= PIN_NUM) {
            return SPARE;
        }
        if (rollIdxSize >= ROLL_NUM_PER_FRAME) {
            return MISS;
        }
        if (countOfPins >= PIN_NUM) {
            return STRIKE;
        }
        return UNFINISHED;
    }
}
