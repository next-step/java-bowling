package bowling.domain.state;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;

public interface State {

    boolean isFinished();

    Score createScore();

    State bowl(Pin pin);

    Score calculateAdditionalScore(Score score);

    String desc();

}
