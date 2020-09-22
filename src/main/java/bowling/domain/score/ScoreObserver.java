package bowling.domain.score;

import bowling.domain.roll.Roll;

public interface ScoreObserver {
    boolean update(Roll roll);
}
