package bowling.domain.frames;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;
import bowling.domain.pitchings.Pitchings;
import bowling.dto.FrameDto;

import java.util.Optional;

public abstract class Frame {
    protected Integer totalScore;
    final int index;

    public Frame(int index) {
        this.index = index;
    }

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        getPitchings().addPitching(knockDownPins);
    }

    public boolean isEnd() {
        return getPitchings().isEnd();
    }

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

    public FrameDto convertToFrameDto() {
        return FrameDto.of(getPitchings(), getTotalScore());
    }

    public abstract Pitchings getPitchings();

    public abstract Frame initNextFrame();

    public abstract Frame getLastFrame();

    public abstract Frame getNextFrame();

    protected abstract Optional<Integer> getScore();

    protected abstract Integer getPreviousTotalScore();

    abstract Optional<Pitching> getFirstPitching();

    abstract Optional<Pitching> getSecondPitching();
}
