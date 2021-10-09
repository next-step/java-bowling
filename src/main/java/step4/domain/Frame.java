package step4.domain;

import step4.domain.state.State;

public interface Frame {

    Score calculateScoreFromNextFrame(Score state);

    void throwBowl(int i);

    Frame createFrame(int i);

    Score getScore();

    boolean isFinish();

    int round();

    Frame next();

    String getSymbol();

    boolean isGameEnd();
}
