package bowling.domain.state;

import bowling.domain.Score;

public interface State {
    boolean isCalculated();
    boolean isOver();
    State bowl(int pins);
    Score calculateScore();
    Score addBonusScore(Score prevScore);
    int getNumberOfTries();
    String display();
}
