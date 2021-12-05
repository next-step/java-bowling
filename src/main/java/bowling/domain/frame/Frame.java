package bowling.domain.frame;

import bowling.annotations.GetterForUI;
import bowling.domain.value.FrameNumber;
import bowling.domain.value.Pins;
import bowling.domain.value.Score;

public abstract class Frame {
    protected Score score;
    protected FrameNumber frameNumber;

    public abstract boolean isFrameOver();

    public abstract boolean isGameOver();

    public abstract void accumulateScore();

    public boolean canCalculateScore() {
        return score.canCalculateScore();
    }

    public void accumulateScore(int countOfPins) {
        this.score = score.accumulateScore(countOfPins);
    }

    public final void knockedDown(Pins pins) {
        pitch(pins);
    }

    abstract void pitch(Pins pins);

    @GetterForUI
    public final FrameNumber getCurrentFrameNumber() {
        return frameNumber;
    }

    @GetterForUI
    public Score getScore() {
        return score;
    }

    @GetterForUI
    public abstract String getMark();
}
