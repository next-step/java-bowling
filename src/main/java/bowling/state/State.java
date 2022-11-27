package bowling.state;

import bowling.Pin;
import bowling.Score;

public interface State {

    State bowl(Pin falledPins);

    boolean isFinished();

    Score score();

    Score calculateAdditionalScore(Score beforeScore);

    String getDesc();

    boolean canAddBonusPins();
}
