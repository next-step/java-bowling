package bowling.domain.state;

import bowling.domain.Score;

public interface State {
    State bowl(int pinCount);
    boolean isFinished();
    boolean hasBonus();

    Score makeScore();

    Score additionalCalculate(Score beforeScore);
}
