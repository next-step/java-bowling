package bowling.domain.state;

import bowling.domain.frame.Pin;
import bowling.domain.frame.Score;

import java.util.List;

public interface State {
    boolean isFinished();

    State bowl(Pin pin);

    Score score();

    Score calculateAdditionalScore(Score beforeScore);

    List<Pin> pins();
}
