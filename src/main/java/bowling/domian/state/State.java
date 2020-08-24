package bowling.domian.state;

import bowling.domian.frame.Score;

public interface State {
    State bowl(int falledPinsCount);

    boolean isFinished();

    boolean canGetScore();

    Score getScore();

    Score getFinalScore();

    Score calculateAdditional(Score lastScore);

    String getDesc();
}
