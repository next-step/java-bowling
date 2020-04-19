package bowling.domain.frame;

import bowling.domain.pitch.Pitch;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FinalFrame implements Frame {
    private static final int MAX_PITCH_SIZE = 3;

    private FramePitch framePitch;
    private Score score;

    public FinalFrame() {
        this.framePitch = new FramePitch();
    }

    private boolean hasThirdChance() {
        return framePitch.isSecondPitchSpare() ||
                framePitch.isFirstPitchStrike();
    }

    @Override public boolean addPinCount(int pinCount) {
        if (isDone()) {
            return false;
        }

        return framePitch.add(pinCount);
    }

    @Override public Optional<Integer> getScore() {
        if (!isDone()) {
            return Optional.empty();
        }

        if (!Objects.isNull(score)) {
            return Optional.of(score.getScore());
        }

        score = framePitch.getScore();
        return Optional.of(score.getScore());
    }

    @Override public Optional<Score> getScoreForOnePitch() {
        return framePitch.getFirstPitchScore();
    }

    @Override public Optional<Score> getScoreForTwoPitches() {
        Optional<Score> firstScore = framePitch.getFirstPitchScore();
        Optional<Score> secondScore = framePitch.getSecondPitchScore();
        if (firstScore.isPresent() && secondScore.isPresent()) {
            return firstScore
                    .map(fs -> fs.add(secondScore.get()));
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
