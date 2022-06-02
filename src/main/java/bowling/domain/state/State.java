package bowling.domain.state;

import bowling.domain.Score;

public interface State {
    State bowl(int pins);
    Score getScore();
    String expression();
    Score calculateScore(Score beforeScore);
    boolean isFinish();
}
