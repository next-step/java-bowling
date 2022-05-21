package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.score.Score;

public interface State {

    State pitch(Pins pins);

    boolean isFrameEnd();

    String getSymbol();

    Score score();

    Score calculateScore(Score beforeScore);

}
