package step3;

import step3.state.Ready;
import step3.state.State;

public interface Frame {
    public Frame bowl(int fallenPins);

    public Score getScore();

    public boolean isGameEnd();

    public State getState();
    
    public boolean isFinish();

    Score calculateAdditionalScore(Score score);
}
