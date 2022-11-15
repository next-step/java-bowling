package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.List;

public interface State {
    State bowl(Pin pin);
    boolean isFinished();
    Score getScore();
    Score calculateAdditionalScore(Score score);
    List<Pin> pins();
}
