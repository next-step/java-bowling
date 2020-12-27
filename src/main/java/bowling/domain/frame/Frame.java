package bowling.domain.frame;

import bowling.domain.Bowling;
import bowling.domain.score.Pins;
import bowling.domain.state.State;

public interface Frame {

    static Frame init() {
        return new NormalFrame(Bowling.INDEX_ZERO);
    }

    Frame next();

    State stroke(int playerIndex, Pins pins);

    State spare(int playerIndex, Pins pins);

    State getState(int playerIndex);

    State getLastState();

    String getSymbol(int playerIndex);

    String getLastStateSymbol();

    int getFrameScore(int playerIndex);

    int getLastStateFrameScore();

    int getFirstScore(int playerIndex);

    int getLastStateFirstScore();

    int getSecondScore(int playerIndex);

    int getLastStateSecondScore();
}
