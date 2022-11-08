package bowling.domain.frame;

import bowling.domain.BowlingGameFrameRecord;

public interface Frame {
    void bowl(int falledPins);

    Frame createNextFrame();

    BowlingGameFrameRecord createFrameRecord();

    int getFrameNumber();

    boolean isFinish();
}
