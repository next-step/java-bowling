package bowling.domain.state;

import bowling.domain.Score;

public interface State {
    State bowl(int pinCount);
    boolean isEnd();
    boolean hasBonus();

    Score makeScore();

    Score additionalCalculate(Score beforeScore);

    boolean isBonus();
    boolean isSpare();
    boolean isFinished();
    boolean isRunning();
}
