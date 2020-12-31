package step2.domain.state;

import step2.domain.Score;

public interface State {

    State bowl(int fallingPins);

    boolean isFinish();

    Score getScore();

    Score calculateAdditionalScore(Score score);

}
