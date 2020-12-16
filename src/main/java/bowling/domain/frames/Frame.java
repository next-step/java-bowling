package bowling.domain.frames;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;
import bowling.domain.pitchings.Pitchings;

public abstract class Frame implements FrameService, FrameViewDto {
    protected final Pitchings pitchings;
    protected Integer totalScore;
    private final Frame previousFrame;

    public Frame(Pitchings pitchings, Frame previousFrame) {
        this.pitchings = pitchings;
        this.previousFrame = previousFrame;
    }

    @Override
    public void setKnockDownPins(KnockDownPins knockDownPins) {
        pitchings.addPitching(knockDownPins);
    }

    @Override
    public boolean isEnd() {
        return pitchings.isEnd();
    }

    @Override
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

    @Override
    public Pitchings getPitchings() {
        return pitchings;
    }

    protected abstract Integer calculateScore();

    abstract public Frame initNextFrame();

    abstract Pitching getFirstPitching();

    abstract Pitching getSecondPitching();
}
