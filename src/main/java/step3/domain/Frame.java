package step3.domain;

import step3.state.State;

public abstract class Frame {

    public abstract Frame bowl(int fallenPins);

    public abstract Score getScore();

    public abstract boolean isGameEnd();

    public abstract State getState();

    public abstract boolean isFinish();

    public abstract Score calculateAdditionalScore(Score score);

    public abstract int number();

    public abstract String getSymbol();

    public abstract Frame createFrame();
}
