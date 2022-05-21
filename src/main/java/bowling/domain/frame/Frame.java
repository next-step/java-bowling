package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.Score;

public interface Frame {

    int number();

    Frame bowl(Pin no);

    Score score();

    boolean finished();

    Score additionalScore(Score beforeScore);

    String expression();
}
