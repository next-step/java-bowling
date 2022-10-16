package bowling.domain.scorestrategy;

import bowling.domain.Score;

@FunctionalInterface
public interface ScoreStrategy {
    Score getScore(int bound);
}
