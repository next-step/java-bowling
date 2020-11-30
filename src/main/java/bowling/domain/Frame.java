package bowling.domain;

import bowling.dto.FrameDto;

class Frame {
    private final int startIndex;
    private FrameEnum frameEnum;

    private Frame(int startIndex, FrameEnum frameEnum) {
        this.startIndex = startIndex;
        this.frameEnum = frameEnum;
    }

    static Frame of(Rolls rolls) {
        int startIndex = rolls.size() - 1;
        int countOfRolls = 1;
        int countOfPins = rolls.sum(startIndex, countOfRolls);
        FrameEnum frameEnum = FrameEnum.get(countOfRolls, countOfPins);
        return new Frame(startIndex, frameEnum);
    }

    void update(Rolls rolls) {
        int countOfRolls = 2;
        int countOfPins = rolls.sum(startIndex, countOfRolls);
        frameEnum = FrameEnum.get(countOfRolls, countOfPins);
    }

    boolean isFinished() {
        return frameEnum != FrameEnum.UNFINISHED;
    }

    boolean isBonus() {
        return frameEnum == FrameEnum.STRIKE
                || frameEnum == FrameEnum.SPARE;
    }

    int score(Rolls rolls) {
        return frameEnum == FrameEnum.MISS
                ? rolls.sum(startIndex, 2)
                : frameEnum == FrameEnum.SPARE
                ? rolls.sum(startIndex, 3)
                : frameEnum == FrameEnum.STRIKE
                ? rolls.sum(startIndex, 4)
                : -1;
    }

    FrameDto exportFrameDto() {
        return new FrameDto(frameEnum);
    }
}
