package bowling.domain.frame;

import bowling.domain.frame.score.EmptyScore;
import bowling.domain.frame.score.Score;
import bowling.domain.pitch.Pitch;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class NormalFrame implements Frame {
    private static final int MAX_PITCH_SIZE = 2;
    private static final int ONE = 1;
    private static final int ZERO = 0;

    private FramePitch framePitch;
    private Frame next;
    private Score score;

    public NormalFrame() {
        this.framePitch = new FramePitch();
        score = new EmptyScore();
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
        return score.calculateScore(next);
    }

    @Override public Frame createNext() {
        next = new NormalFrame();
        return next;
    }

    @Override public Frame createNext(Frame frame) {
        next = frame;
        return frame;
    }

    @Override public Optional<Integer> getPinCountForOnePitch() {
        return framePitch.getFirstPitchPinCount();
    }

    @Override public Optional<Integer> getPinCountForTwoPitches() {
        if (framePitch.size() == ZERO) {
            return Optional.empty();
        }
        if (framePitch.size() == ONE) {
            return sumWithNextPitch();
        }

        return Optional.of(framePitch.getPinCountTotal());
    }

    @Override public boolean isDone() {
        return framePitch.size() == MAX_PITCH_SIZE ||
                framePitch.isFirstPitchStrike();
    }

    private Optional<Integer> sumWithNextPitch() {
        if (Objects.isNull(next)) {
            return Optional.empty();
        }

        Optional<Integer> nextScore = next.getPinCountForOnePitch();
        if (nextScore.isPresent()) {
            return score.calculateScore()
                    .map(ownScore -> ownScore + nextScore.get());
        }

        return Optional.empty();
    }

    @Override public List<Pitch> getFramePitch() {
        return framePitch.getPitches();
    }

    @Override public boolean isLast() {
        return false;
    }
}
