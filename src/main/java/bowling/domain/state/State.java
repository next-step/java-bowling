package bowling.domain.state;

import bowling.domain.pin.FallenPin;
import bowling.domain.score.Score;

public interface State {

    State bowl(FallenPin fallenPin);

    boolean isFinished();

    String description();

    int tries();

    Score getScore();

    Score addScore(Score previousScore);
}
