package bowling.domain;

public class FrameSet {

    private int playCount = 2;
    private int remainPinCount = 10;

    public Frame play(int hitCount) {
        playCount--;
        remainPinCount -= hitCount;

        if (hitCount == 10) {
            return Frame.STRIKE;
        }

        if (playCount == 0 && remainPinCount == 0) {
            return Frame.SPARE;
        }

        if (playCount == 0 && remainPinCount == 10) {
            return Frame.GUTTER;
        }

        if (playCount == 0 && remainPinCount > 0) {
            return Frame.MISS;
        }

        if (remainPinCount < 10) {
            return Frame.HIT;
        }

        return Frame.NO_HIT;
    }
}
