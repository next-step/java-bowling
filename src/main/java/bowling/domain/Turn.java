package bowling.domain;

import bowling.domain.score.TurnScore;

import java.util.Objects;

public class Turn {
    protected final TurnScore score;

    public Turn(final TurnScore score) {
        this.score = score;
    }

    public static Turn empty() {
        return InnerLazyClass.EMPTY_TURN;
    }

    public TurnScore value() {
        return score;
    }

    public Turn union(Turn turn) {
        return new Turn(
                TurnScore.of(
                        value().sum(turn.value())
                )
        );
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isGutter() {
        return score.isZero();
    }

    public boolean isAllClear() {
        return score.isAllClear();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Turn turn = (Turn) o;
        return Objects.equals(score, turn.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public String toString() {
        return "Turn{" + "score=" + score + '}';
    }

    private static class InnerLazyClass {
        private static final Turn EMPTY_TURN = new Turn(TurnScore.empty()) {
            @Override
            public boolean isEmpty() {
                return true;
            }
        };
    }
}
