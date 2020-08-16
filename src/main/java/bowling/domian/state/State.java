package bowling.domian.state;

import bowling.domian.frame.Score;

public interface State {
    State bowl(int falledPinsCount);

    boolean isFinished();

    boolean canGetScore();

    Score getScore();

    Score calculateAdditional(Score lastScore);

    String getDesc();
}
