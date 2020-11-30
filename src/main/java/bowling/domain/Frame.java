package bowling.domain;

import bowling.dto.FrameDto;

class Frame {
    private final int rollIndex;
    private FrameEnum frameEnum;

    private Frame(int rollIndex, FrameEnum frameEnum) {
        this.rollIndex = rollIndex;
        this.frameEnum = frameEnum;
    }

    static Frame of(Rolls rolls) {
        int rollIndex = rolls.size() - 1;
        int countOfRolls = 1;
        int countOfPins = rolls.sum(rollIndex, countOfRolls);
        FrameEnum frameEnum = FrameEnum.get(countOfRolls, countOfPins);
        return new Frame(rollIndex, frameEnum);
    }

    void update(Rolls rolls) {
        int countOfRolls = 2;
        int countOfPins = rolls.sum(rollIndex, countOfRolls);
        frameEnum = FrameEnum.get(countOfRolls, countOfPins);
    }

    boolean isFinished() {
        return frameEnum != FrameEnum.UNFINISHED;
    }

    boolean isStrike() {
        return frameEnum == FrameEnum.STRIKE;
    }

    boolean isSpare() {
        return frameEnum == FrameEnum.SPARE;
    }

    int score(Rolls rolls) {
        return isStrike() || isSpare()
                ? rolls.sum(rollIndex, 3)
                : frameEnum == FrameEnum.MISS
                ? rolls.sum(rollIndex, 2)
                : -1;
    }

    FrameDto exportFrameDto() {
        return new FrameDto(frameEnum);
    }
}
