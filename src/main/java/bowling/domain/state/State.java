package bowling.domain.state;

import bowling.domain.Score;

public interface State {
    boolean isStrike();
    boolean isSpare();
    boolean hasNext();
    boolean canAccumulate();
    Score eval();
    State next(int nextHit);
}
