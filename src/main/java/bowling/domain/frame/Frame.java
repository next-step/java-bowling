package bowling.domain.frame;

import bowling.domain.score.Pins;
import bowling.domain.state.State;

public interface Frame {

    Frame stroke(int frameNo, Pins pins);

    Frame spare(int frameNo, Pins pins);

    State getState();

    String getSymbol();

    int getFrameScore();

    int getFirstScore();

    int getSecondScore();

    Frame getNext();

    int getBonusScore();

    int getBonus2Score();

    boolean isVisible();
}
