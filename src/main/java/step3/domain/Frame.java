package step3.domain;

import step3.domain.state.State;

public interface Frame {
    public Frame bowl(int fallenPins);

    public Score getScore();

    public boolean isGameEnd();

    public State getState();
    
    public boolean isFinish();

    Score calculateAdditionalScore(Score score);
}
