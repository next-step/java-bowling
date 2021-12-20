package bowling;

import bowling.domain.factory.ScoresFactory;
import bowling.domain.scores.Scores;
import java.util.Objects;

public class Frame {

    private static final int MIN_ROUND = 0;
    private static final int MAX_ROUND = 9;

    private final int round;
    private final Scores scores;

    public Frame(int round, ScoresFactory scoresFactory) {
        this(round, scoresFactory.create(round));
    }

    public Frame(int round, Scores scores) {

        valid(round);

        this.round = round;
        this.scores = scores;
    }

    public boolean isClosedStroke() {
        return scores.isClosed();
    }

    private void valid(int round) {
        if (round < MIN_ROUND) {
            throw new IllegalArgumentException("round 는 음수가 올 수 없어요.");
        }

        if (round > MAX_ROUND) {
            throw new IllegalArgumentException("round 는 9를 넘길 수 없어요.");
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frame frame = (Frame) o;
        return round == frame.round && Objects.equals(scores, frame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, scores);
    }
}
