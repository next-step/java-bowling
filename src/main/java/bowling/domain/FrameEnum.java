package bowling.domain;

import static bowling.asset.Const.PIN_NUM;

public enum FrameEnum {
    STRIKE,
    SPARE,
    MISS,
    UNFINISHED;

    public static final int ROLL_NUM_PER_FRAME = 2;

    static FrameEnum get(int countOfRolls, int countOfPins) {
        if (countOfRolls >= ROLL_NUM_PER_FRAME && countOfPins >= PIN_NUM) {
            return SPARE;
        }
        if (countOfRolls >= ROLL_NUM_PER_FRAME) {
            return MISS;
        }
        if (countOfPins >= PIN_NUM) {
            return STRIKE;
        }
        return UNFINISHED;
    }
}
