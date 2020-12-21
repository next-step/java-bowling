package bowling.domain.state;

import bowling.domain.Score;

public interface State {
    State bowl(int count);

    Pins getPins();

    boolean isFinished();

    Score getScore();

    Score addNextScore(Score score);
}
