package bowling.frame.state;

import bowling.frame.Score;

public interface State {
    State bowl(int falledPins);

    boolean isFinish();

    Score getScore();

    Score calculateAdditionalScore(Score score);

    String getDesc();
}
