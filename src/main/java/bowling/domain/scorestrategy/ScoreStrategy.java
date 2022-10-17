package bowling.domain.scorestrategy;

import bowling.domain.Pin;

@FunctionalInterface
public interface ScoreStrategy {
    Pin getScore(int bound);
}
