package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {
    private static final int BONUS_INCLUDED_BOWLING_TRIES = 3;

    private final List<Bowling> bowlings;

    FinalFrame(List<Bowling> bowlings) {
        this.bowlings = bowlings;
    }

    public static FinalFrame init() {
        return new FinalFrame(List.of(Bowling.init()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinalFrame)) return false;

        FinalFrame that = (FinalFrame) o;

        return Objects.equals(bowlings, that.bowlings);
    }

    @Override
    public int hashCode() {
        return bowlings != null ? bowlings.hashCode() : 0;
    }

    @Override
    public List<Bowling> bowlings() {
        return bowlings;
    }

    @Override
    public Frame bowl(Score score) {
        if (lastBowling().isFinished()) {
            return new FinalFrame(add(score));
        }

        return new FinalFrame(update(score));
    }

    @Override
    public boolean isFinished() {
        if (firstBowling().isStrike() || firstBowling().isSpare()) {
            return totalBowlingTries() == BONUS_INCLUDED_BOWLING_TRIES;
        }

        return firstBowling().isFinished();
    }

    private int totalBowlingTries() {
        return bowlings.stream()
                .mapToInt(Bowling::tries)
                .sum();
    }

    private List<Bowling> add(Score score) {
        List<Bowling> result = new ArrayList<>(bowlings);
        Bowling bowling = Bowling.init();
        result.add(bowling.bowl(score));
        return result;
    }

    private List<Bowling> update(Score score) {
        List<Bowling> result = new ArrayList<>(bowlings);
        result.set(lastIndex(), lastBowling().bowl(score));
        return result;
    }

    private Bowling firstBowling() {
        return bowlings.get(0);
    }

    private Bowling lastBowling() {
        return bowlings.get(lastIndex());
    }

    private int lastIndex() {
        return bowlings.size() - 1;
    }
}
