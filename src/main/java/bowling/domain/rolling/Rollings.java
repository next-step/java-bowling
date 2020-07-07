package bowling.domain.rolling;

import bowling.domain.frame.Score;

import java.util.List;

public interface Rollings {
    void roll(int pinCount);
    boolean isRollingPossible();
    boolean isState(State state);
    List<String> getStates();
    int getLastScore();
    int calculateScore();
    void calculateAdditionalScore(Score score);
    void additionalScoreOfStrike(Score score);
}
