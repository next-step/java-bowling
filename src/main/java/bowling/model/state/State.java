package bowling.model.state;

import bowling.model.Score;

public interface State {

    Score calculateScore(Score score);

    boolean isFinished();

    State bowling(int fallenPin);

    Score score();
}
