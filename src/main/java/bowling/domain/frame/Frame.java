package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.state.State;

public interface Frame {
    int number();
    Frame bowl(int pins);
    State state();
    boolean isGameEnd();
    boolean canCalculateCurrentScore();
    boolean canCalculatePrefixSumScore(Score score);
    Score score();
    Score prefixSumScore(Score beforeScore);
    String mark();
}
