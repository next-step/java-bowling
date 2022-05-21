package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.Score;

public interface State {

    State bowl(Pin no);

    Score score();

    boolean finished();

    Score additionalScore(Score beforeScore);

    String expression();
}
