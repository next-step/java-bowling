package bowling.domain.frame;

import bowling.annotations.GetterForUI;
import bowling.domain.value.Pins;
import bowling.domain.value.Score;

public abstract class Frame {
    protected Score score;

    public abstract boolean isFrameOver();

    public abstract boolean isGameOver();

    public abstract void pitch(Pins pins);

    public boolean canCalculateScore() {
        return score.canCalculateScore();
    }

    public void accumulateScore(int countOfPins) {
        this.score = score.accumulateScore(countOfPins);
    }

    @GetterForUI
    public Score getScore() {
        return score;
    }

    @GetterForUI
    public abstract String getMark();
}
