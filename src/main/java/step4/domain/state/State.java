package step4.domain.state;

import step4.domain.Score;

public interface State {

    State bowl(int pinCount);

    Score score();

    Score addScore(Score score);

    boolean isFinished();

    String marks();
}
