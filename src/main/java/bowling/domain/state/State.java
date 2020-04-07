package bowling.domain.state;

import bowling.domain.frame.Score;

public interface State {

    State bowl(Pin pins);

    boolean isFinish();

    String display();

    Score getScore();

    Score calculateByBeforeScore(Score before);
}
