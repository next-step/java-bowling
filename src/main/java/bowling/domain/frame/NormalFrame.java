package bowling.domain.frame;

import bowling.domain.score.*;
import bowling.domain.pitch.Pitch;

import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {
    private static final int MAX_PITCH_SIZE = 2;
    private static final int ONE = 1;
    private static final int ZERO = 0;

    private FramePitch framePitch;
    private Frame next;
    private ScoreCalculator scoreCalculator;

    public NormalFrame() {
        this.framePitch = new FramePitch();
        scoreCalculator = new EmptyScoreCalculator();
    }

    @Override public boolean addPinCount(int pinCount) {
        if (isDone()) {
            return false;
        }

        if (framePitch.add(pinCount)) {
            scoreCalculator = scoreCalculator.add(framePitch.getLastPitch());
            return true;
        }
        return false;
    }

    @Override public Score getScore() {
        return scoreCalculator.calculateScore(next);
    }

    @Override public Frame createNext() {
        next = new NormalFrame();
        return next;
    }

    @Override public Frame createNext(Frame frame) {
        next = frame;
        return frame;
    }

    @Override public Score getScoreForOnePitch() {
        return framePitch.getFirstPitchScore();
    }

    @Override public Score getScoreForTwoPitches() {
        if (framePitch.size() == ZERO) {
            return EmptyScore.valueOf();
        }
        if (framePitch.size() == ONE) {
            return sumWithNextOnePitch();
        }

        return new CompleteScore(framePitch.getPinCountTotal());
    }

    @Override public boolean isDone() {
        return framePitch.size() == MAX_PITCH_SIZE ||
                framePitch.isFirstPitchStrike();
    }

    private Score sumWithNextOnePitch() {
        if (Objects.isNull(next)) {
            return EmptyScore.valueOf();
        }
        return next.getScoreForOnePitch()
                .add(scoreCalculator.calculateScore());
    }

    @Override public List<Pitch> getFramePitch() {
        return framePitch.getPitches();
    }

    @Override public boolean isLast() {
        return false;
    }
}
