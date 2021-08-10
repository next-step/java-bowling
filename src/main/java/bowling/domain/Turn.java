package bowling.domain;

import bowling.domain.score.TurnScore;

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
        return score.isEmpty();
    }

    public boolean isGutter() {
        return score.isZero();
    }

    public boolean isAllClear() {
        return score.isAllClear();
    }

    @Override
    public String toString() {
        return "Turn{" + "score=" + score + '}';
    }

    private static class InnerLazyClass {
        private static final Turn EMPTY_TURN = new Turn(TurnScore.empty());
    }
}
