package step3.state;

import step3.Score;

public interface State {
    State bowl(int fallenPins);

    Score score();

    Score calculateAdditionalScore(Score beforeScore);

    boolean isFinish();
}
