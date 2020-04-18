package bowling.domain.frame;

import bowling.domain.pitch.Pitch;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class NormalFrame implements Frame {
    private static final int MAX_PIN_COUNT_SIZE = 2;
    private static final int MAX_PIN_COUNT = 10;

    private Pitches pitches;
    private Frame next;
    private Score score;

    public NormalFrame() {
        this.pitches = new Pitches();
    }

    @Override public boolean addPinCount(int pinCount) {
        if (!isAddable(pinCount) || isDone()) {
            return false;
        }

        boolean result = pitches.add(pinCount);
        if (isDone()) {
            score = new Score(pitches);
        }

        return result;
    }

    private boolean isAddable(int pinCount) {
        if (pitches.isFull(MAX_PIN_COUNT_SIZE)) {
            return false;
        }

        Optional<Pitch> firstPinCount = pitches.getFirst();
        return firstPinCount.map(count -> !count.isOverMaxAfterAdd(pinCount))
                .orElse(true);
    }

    @Override public Optional<Integer> getScore() {
        if (Objects.isNull(score)) {
            return Optional.empty();
        }
        if (score.isAbleToGetScore()) {
            return score.getScore();
        }
        return calculateScoreWithNextScore()
                .map(additionalScore -> {
                    score.setAdditionalScore(additionalScore);
                    return score.getScore();
                })
                .map(Optional::get);
    }

    private Optional<Score> calculateScoreWithNextScore() {
        if (pitches.isLastPitchStrike()) {
            return next.getScoreForTwoPitches();
        } else if (pitches.isLastPitchSpare()) {
            return next.getScoreForOnePitch();
        }

        return Optional.empty();
    }

    @Override public boolean isDone() {
        if (pitches.isEmpty()) {
            return false;
        }

        if (pitches.isFull(MAX_PIN_COUNT_SIZE)) {
            return true;
        }

        return pitches.getPinCountTotal() == MAX_PIN_COUNT;
    }

    @Override public List<Pitch> getPitches() {
        return pitches.getPitches();
    }

    @Override public boolean isLast() {
        return false;
    }

    @Override public NormalFrame createNext() {
        NormalFrame normalFrame = new NormalFrame();
        next = normalFrame;
        return normalFrame;
    }

    @Override public Frame createNext(Frame frame) {
        next = frame;
        return frame;
    }

    @Override public Optional<Score> getScoreForTwoPitches() {
        if (pitches.size() == MAX_PIN_COUNT_SIZE) {
            return Optional.of(score);
        } else if (pitches.isLastPitchStrike()) {
            return next.getScoreForOnePitch()
                    .map(nextScore -> score.add(nextScore));
        }
        return Optional.empty();
    }

    @Override public Optional<Score> getScoreForOnePitch() {
        return pitches.getFirst()
                .map(pitch -> new Score(pitch.getCount()));
    }
}
