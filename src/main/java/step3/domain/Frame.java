package step3.domain;

import step3.state.State;

public interface Frame {

    Frame bowl(int fallenPins);

    Score getScore();

    boolean isGameEnd();

    State getState();

    boolean isFinish();

    Score calculateAdditionalScore(Score score);

    int number();

    String getSymbol();

    Frame createFrame();
}
