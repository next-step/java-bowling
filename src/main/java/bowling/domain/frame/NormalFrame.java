package bowling.domain.frame;

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

        return calculateScore().map(s -> {
            this.score = s;
            return this.score.getScore();
        });
    }

    private Optional<Score> calculateScore() {
        Score framePitchScore = framePitch.getScore();
        if (framePitchScore.isSatisfied()) {
            return Optional.of(framePitchScore);
        }

        return getAdditionalScore().map(as -> {
            framePitchScore.setAdditionalScore(as);
            return framePitchScore;
        });
    }

    private Optional<Score> getAdditionalScore() {
        if(Objects.isNull(next)) {
            return Optional.empty();
        }

        return framePitch.getBonusScore(next);
    }

    @Override public Frame createNext() {
        next = new NormalFrame();
        return next;
    }

    @Override public Frame createNext(Frame frame) {
        next = frame;
        return frame;
    }

    @Override public Optional<Score> getScoreForOnePitch() {
        return framePitch.getFirstPitchScore();
    }

    @Override public Optional<Score> getScoreForTwoPitches() {
        if (framePitch.size() == ZERO) {
            return Optional.empty();
        }
        if (framePitch.size() == ONE) {
            return sumWithNextPitch();
        }

        return Optional.of(framePitch.getScore());
    }

    @Override public boolean isDone() {
        return framePitch.size() == MAX_PITCH_SIZE ||
                framePitch.isFirstPitchStrike();
    }

    private Optional<Score> sumWithNextPitch() {
        if (Objects.isNull(next)) {
            return Optional.empty();
        }
        return next.getScoreForOnePitch()
                .map(s -> s.add(framePitch.getScore()));
    }

    @Override public List<Pitch> getFramePitch() {
        return framePitch.getPitches();
    }

    @Override public boolean isLast() {
        return false;
    }
}
