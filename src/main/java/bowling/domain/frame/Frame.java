package bowling.domain.frame;

import bowling.domain.dto.BowlingGameFrameRecord;

public abstract class Frame {
    public static final int START_FRAME = 1;
    public static final int LAST_FRAME = 10;

    public abstract void bowl(int falledPins);

    public abstract Frame createNextFrame();

    public abstract BowlingGameFrameRecord createFrameRecord();

    public abstract Score calculateBonusScore(Score previousFrameScore);

    public abstract int getFrameNumber();

    public abstract boolean isFrameFinish();
}
