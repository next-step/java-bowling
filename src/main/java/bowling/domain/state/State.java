package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public interface State {
    State play(Pin fallenPin);

    boolean isFinish();

    int getPitchCount();

    Score getScore();

    int getTotalCount();

    boolean isStrikeOrSpare();
}
