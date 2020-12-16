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
        if (canNotCalculate()) {
            return Optional.empty();
        }

        Integer totalScore = calculateTotalScore();
        return Optional.of(totalScore);
    }

    private Integer calculateTotalScore() {
        Integer score = getScore().orElseThrow(IllegalStateException::new);
        if (isFirstFrame()) {
            totalScore = score;
            return totalScore;
        }

        Integer previousFrameTotalScore = getPreviousTotalScore();
        totalScore = previousFrameTotalScore + score;
        return totalScore;
    }

    private boolean canNotCalculate() {
        return !getScore().isPresent();
    }

    private boolean isFirstFrame() {
        return index == 1;
    }

    public abstract FrameImpl initNextFrame();

    public abstract FrameImpl getLastFrame();

    public abstract FrameImpl getNextFrame();

    protected abstract Optional<Integer> getScore();

    protected abstract Integer getPreviousTotalScore();

    abstract Optional<Pitching> getFirstPitching();

    abstract Optional<Pitching> getSecondPitching();
}
