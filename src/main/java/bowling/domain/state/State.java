package bowling.domain.state;

import bowling.domain.Score;

public interface State {
    State bowl(int countOfPins);
    Score getScore();
    String expression();
    Score calculateAddScore(Score beforeScore);
    boolean isFinish();
}
