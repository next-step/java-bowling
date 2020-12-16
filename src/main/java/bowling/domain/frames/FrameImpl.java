package bowling.domain.frames;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;

import java.util.Optional;

public abstract class FrameImpl implements Frame, FrameViewDto {
    protected Integer totalScore;
    final int index;

    public FrameImpl(int index) {
        this.index = index;
    }

    @Override
    public void setKnockDownPins(KnockDownPins knockDownPins) {
        getPitchings().addPitching(knockDownPins);
    }

    @Override
    public boolean isEnd() {
        return getPitchings().isEnd();
    }

    @Override
    public Optional<Integer> getTotalScore() {
        if (!isEnd()) {
            return Optional.empty();
        }

        if (!getScore().isPresent()) {
            return Optional.empty();
        }

        if (isFirstFrame()) {
            totalScore = getScore().get();
            return Optional.of(totalScore);
        }

        FrameImpl previousFrame = getPreviousFrame();
        Optional<Integer> previousFrameTotalScore = previousFrame.getTotalScore();
        totalScore = previousFrameTotalScore.orElse(0) + getScore().get();
        return Optional.of(totalScore);
    }

    private boolean isFirstFrame() {
        return index == 1;
    }

    protected abstract Optional<Integer> getScore();

    abstract public FrameImpl initNextFrame();

    abstract Optional<Pitching> getFirstPitching();

    abstract Optional<Pitching> getSecondPitching();

    public abstract FrameImpl getLastFrame();

    public abstract FrameImpl getNextFrame();

    protected abstract FrameImpl getPreviousFrame();
}
