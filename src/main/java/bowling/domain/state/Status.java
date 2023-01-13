package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public interface Status {

    boolean isFinished();

    Status bowl(Pin pin);

    Score score();

    Score calculateScore(Score lastScore);

}
