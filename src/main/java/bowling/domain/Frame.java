package bowling.domain;

import bowling.exception.CanNotAddRollException;

import java.util.LinkedList;
import java.util.List;

import static bowling.assets.Const.PIN_NUM;

class Frame {
    private final List<Roll> rolls = new LinkedList<>();

    void add(Roll roll) {
        if (isFinished()) {
            throw CanNotAddRollException.getInstance();
        }
        rolls.add(roll);
    }

    boolean isStrike() {
        return rolls.size() == 1
                && Roll.sum(rolls) == PIN_NUM;
    }

    boolean isSpare() {
        return rolls.size() == 2
                && Roll.sum(rolls) == PIN_NUM;
    }

    boolean isMiss() {
        return rolls.size() == 2
                && Roll.sum(rolls) < PIN_NUM;
    }

    boolean isFinished() {
        return isStrike()
                || isSpare()
                || isMiss();
    }

    FrameEnum frameEnum() {
        return isStrike()
                ? FrameEnum.STRIKE
                : isSpare()
                ? FrameEnum.SPARE
                : isMiss()
                ? FrameEnum.MISS
                : FrameEnum.UNFINISHED;
    }
}
