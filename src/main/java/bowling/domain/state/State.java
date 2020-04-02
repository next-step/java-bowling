package bowling.domain.state;

import bowling.domain.frame.Frame2;
import bowling.domain.frame.Score;

public interface State {

    State bowl(int pins);

    boolean isFinish();

    String display();

    Score getScore();

    Score calculateByBeforeScore(Score before);

    void renewScore(Score score);

    Frame2 frame(int frameNumber);
}
