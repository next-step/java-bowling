package bowling.domain.frame;

import bowling.domain.Score;

public interface Frame {
    int number();
    Frame bowl(int pins);
    boolean isGameEnd();
    boolean canCalculateCurrentScore();
    boolean canCalculatePrefixSumScore(Score score);
    Score score();
    Score prefixSumScore(Score beforeScore);
    String mark();
}
