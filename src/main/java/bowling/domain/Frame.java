package bowling.domain;

import bowling.exception.FrameException;

class Frame {
    private final int startIndex;
    private int offset = 1;

    Frame(int startIndex) {
        this.startIndex = startIndex;
    }

    void increaseOffset() {
        if (offset >= 2) {
            throw new FrameException("한 프레임에서는 두번만 투구할 수 있습니다.");
        }
        offset++;
    }

    int score(Rolls rolls) {
        FrameEnum frameEnum = frameEnum(rolls);
        return frameEnum == FrameEnum.MISS
                ? rolls.sum(startIndex, 2)
                : frameEnum == FrameEnum.SPARE
                ? rolls.sum(startIndex, 3)
                : frameEnum == FrameEnum.STRIKE
                ? rolls.sum(startIndex, 4)
                : -1;
    }

    FrameEnum frameEnum(Rolls rolls) {
        int countOfPins = rolls.sum(startIndex, offset);
        return FrameEnum.get(offset, countOfPins);
    }
}
