package bowling.domain;

import static bowling.asset.Const.PIN_NUM;

public enum FrameEnum {
    STRIKE,
    SPARE,
    MISS,
    UNFINISHED;

    public static final int ROLL_NUM_PER_FRAME = 2;

    static FrameEnum get(int countOfRolls, int countOfPins) {
        return countOfRolls >= ROLL_NUM_PER_FRAME && countOfPins >= PIN_NUM
                ? SPARE
                : countOfRolls >= ROLL_NUM_PER_FRAME
                ? MISS
                : countOfPins >= PIN_NUM
                ? STRIKE
                : UNFINISHED;
    }
}
