package bowling.domain.frame;

import bowling.annotations.GetterForUI;
import bowling.domain.value.FrameNumber;
import bowling.domain.value.FramePins;
import bowling.domain.value.Pins;
import bowling.domain.value.Score;

public abstract class Frame {
    protected static final int STRIKE_OR_SPARE_COUNT = 10;

    protected Score score;
    protected FramePins framePins;
    protected FrameNumber frameNumber;

    public abstract boolean isFrameOver();

    public abstract boolean isFinalFrameOver();

    public abstract void accumulateScore();

    abstract void pitch(Pins pins);

    abstract void countScore(Pins pins);

    public final void knockedDown(Pins pins) {
        pitch(pins);
        countScore(pins);
    }

    public boolean isAccumulateScore() {
        return !score.canCalculateScore();
    }

    public void accumulateScore(int countOfPins) {
        this.score = score.bowl(countOfPins);
    }

    @GetterForUI
    public final FrameNumber getCurrentFrameNumber() {
        return frameNumber;
    }

    @GetterForUI
    public FramePins getPins() {
        return framePins;
    }

    @GetterForUI
    public Score getScore() {
        return score;
    }
}
