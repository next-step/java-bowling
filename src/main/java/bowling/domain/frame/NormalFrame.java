package bowling.domain.frame;

import java.util.List;
import java.util.Optional;

public class NormalFrame implements Frame {
    private static final int MAX_PIN_COUNT_SIZE = 2;
    private static final int MAX_PIN_COUNT = 10;

    private Pitches pitches;
    private Frame next;

    public NormalFrame() {
        this.pitches = new Pitches();
    }

    @Override public boolean addPinCount(int pinCount) {
        if (!isAddable(pinCount) || isDone()) {
            return false;
        }

        return pitches.add(pinCount);
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
        if (!isDone()) {
            return Optional.empty();
        }

        int currentScore = pitches.getPinCountTotal();
        return calculateScoreWithNextScore(currentScore);
    }

    private Optional<Integer> calculateScoreWithNextScore(int currentScore) {
        if (pitches.isLastPitchStrike()) {
            return next.getScoreForTwoPitches()
                    .map(nextScore -> nextScore + currentScore);
        } else if (pitches.isLastPitchSpare()) {
            return next.getScoreForOnePitch()
                    .map(nextScore -> nextScore + currentScore);
        }

        return Optional.of(currentScore);
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

    @Override public Optional<Integer> getScoreForTwoPitches() {
        if (pitches.size() == MAX_PIN_COUNT_SIZE) {
            return Optional.of(pitches.getPinCountTotal());
        } else if (pitches.isLastPitchStrike()) {
            return next.getScoreForOnePitch()
                    .map(score -> score + pitches.getPinCountTotal());
        }
        return Optional.empty();
    }

    @Override public Optional<Integer> getScoreForOnePitch() {
        return pitches.getFirst().map(Pitch::getCount);
    }
}
