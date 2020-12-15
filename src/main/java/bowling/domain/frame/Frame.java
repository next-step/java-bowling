package bowling.domain.frame;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;
import bowling.domain.pitchings.Pitchings;

public abstract class Frame {
    private final Pitchings pitchings;
    private final Frame previousFrame;
    protected Integer totalScore;

    public Frame(Pitchings pitchings, Frame previousFrame) {
        this.pitchings = pitchings;
        this.previousFrame = previousFrame;
    }

    abstract public Frame initNextFrame();

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        pitchings.addPitching(knockDownPins);
    }

    public Pitchings getPitchings() {
        return pitchings;
    }

    public boolean isEnd() {
        return pitchings.isEnd();
    }

    public abstract Integer calculateScore();

    public Integer getTotalScore() {
        if (!isEnd()) {
            return null;
        }

        if (isFirstFrame()) {
            totalScore = calculateScore();
            return totalScore;
        }

        totalScore = previousFrame.getTotalScore() + calculateScore();
        return totalScore;
    }

    private boolean isFirstFrame() {
        return previousFrame == null;
    }

    public abstract Pitching getFirstPitching();

    public abstract Pitching getSecondPitching();
}
