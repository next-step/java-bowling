package bowling.domain.frame;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class NormalFrame implements Frame {
    private static final int MAX_PIN_COUNT_SIZE = 2;
    private static final int MAX_PIN_COUNT = 10;

    private Pitches pitches;
    private Frame next;
    private Frame previous;

    public NormalFrame() {
        this(null);
    }

    public NormalFrame(Frame previous) {
        this.pitches = new Pitches();
        this.previous = previous;
    }

    @Override public boolean addPinCount(int pinCount) {
        if (!isAddable(pinCount)) {
            return false;
        }

        if (isDone()) {
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

    public FinalFrame createFinal() {
        next = new FinalFrame(this);
        return (FinalFrame) next;
    }

    @Override public Optional<Integer> getScore() {
        if (!isDone() || pitches.isEmpty()) {
            return Optional.empty();
        }

        int previousScore = getPreviousScore();
        int currentScore = pitches.getPinCountTotal();
        if (pitches.isLastPitchStrike()) {
            return next.getScoreForTwoPitches()
                    .map(nextScore -> nextScore + currentScore + previousScore);
        } else if (pitches.isLastPitchSpare()) {
            return next.getScoreForOnePitch()
                    .map(nextScore -> nextScore + currentScore + previousScore);
        }

        return Optional.of(currentScore + previousScore);
    }

    private int getPreviousScore() {
        if (Objects.isNull(previous)) {
            return 0;
        }

        return previous.getScore().orElse(0);
    }

    @Override public boolean isDone() {
        if (pitches.isFull(MAX_PIN_COUNT_SIZE)) {
            return true;
        }

        return pitches.getPinCountTotal() == MAX_PIN_COUNT;
    }

    @Override public List<Pitch> getPitches() {
        return pitches.getPitches();
    }

    @Override public Frame getNext() {
        return next;
    }

    @Override public boolean isLast() {
        return false;
    }

    @Override public NormalFrame createNext() {
        next = new NormalFrame(this);
        return (NormalFrame) next;
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
