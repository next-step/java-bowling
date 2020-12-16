package bowling.domain.frames;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;
import bowling.domain.pitchings.Pitchings;

import java.util.Optional;

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
    public Optional<Integer> getTotalScore() {
        if (!isEnd()) {
            return Optional.empty();
        }

        if (isFirstFrame()) {
            totalScore = getScore().orElseThrow(NullPointerException::new);
            return Optional.of(totalScore);
        }

        Optional<Integer> previousFrameTotalScore = previousFrame.getTotalScore();
        totalScore = previousFrameTotalScore.orElse(0) + getScore().orElseThrow(NullPointerException::new);
        return Optional.of(totalScore);
    }

    private boolean isFirstFrame() {
        return previousFrame == null;
    }

    @Override
    public Pitchings getPitchings() {
        return pitchings;
    }

    protected abstract Optional<Integer> getScore();

    abstract public Frame initNextFrame();

    abstract Optional<Pitching> getFirstPitching();

    abstract Optional<Pitching> getSecondPitching();
}
