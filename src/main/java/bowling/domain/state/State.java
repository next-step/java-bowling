package bowling.domain.state;

import bowling.domain.frame.Frame;
import bowling.domain.score.Score;

public interface State {
    Frame bowl(Pins pins);

    boolean isFinish();

    Score score();

    Score calculateAdditionalScore(Score beforeScore);

    String description();
}
