package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;

public interface Frame {

    int START_SEQUENCE = 1;
    int LAST_SEQUENCE = 10;

    Frame bowl(Pins fallPins);

    boolean isFinish();

    Score score();

    int sequence();

    Score calculateAdditionalScore(Score beforeScore);

    String description();
}
