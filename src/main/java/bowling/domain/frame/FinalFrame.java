package bowling.domain.frame;

import bowling.domain.score.FinalScore;
import bowling.domain.score.Score;
import bowling.domain.pitch.Pitch;

import java.util.List;
import java.util.Optional;

public class FinalFrame implements Frame {
    private static final int MAX_PITCH_SIZE = 3;

    private FramePitch framePitch;
    private Score score;

    public FinalFrame() {
        this.framePitch = new FramePitch();
        this.score = new FinalScore();
    }

    private boolean hasThirdChance() {
        return framePitch.isSecondPitchSpare() ||
                framePitch.isFirstPitchStrike();
    }

    @Override public boolean addPinCount(int pinCount) {
        if (isDone()) {
            return false;
        }

        if (framePitch.add(pinCount)) {
            score = score.add(framePitch.getLastPitch());
            return true;
        }
        return false;
    }

    @Override public Optional<Integer> getScore() {
        return score.calculateScore();
    }

    @Override public Optional<Integer> getPinCountForOnePitch() {
        return framePitch.getFirstPitchPinCount();
    }

    @Override public Optional<Integer> getPinCountForTwoPitches() {
        Optional<Integer> firstPinCount = framePitch.getFirstPitchPinCount();
        Optional<Integer> secondPinCount = framePitch.getSecondPitchPinCount();

        if (firstPinCount.isPresent() && secondPinCount.isPresent()) {
            return firstPinCount
                    .map(pinCount -> pinCount + secondPinCount.get());
        }

        return Optional.empty();
    }

    @Override public boolean isDone() {
        return framePitch.size() == MAX_PITCH_SIZE ||
                (!hasThirdChance() && framePitch.size() == 2);
    }

    @Override public List<Pitch> getFramePitch() {
        return framePitch.getPitches();
    }

    @Override public Frame createNext() {
        return null;
    }

    @Override public Frame createNext(Frame frame) {
        return null;
    }

    @Override public boolean isLast() {
        return true;
    }
}
