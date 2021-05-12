package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.state.running.Ready;

public interface State {

    static State initialize() {
        return new Ready();
    }

    State bowl(Pins pins);

    State bowl(int pins);

    boolean isFinish();

    boolean isAllPinClear();

    Score score();

    Score calculateAdditionalScore(Score beforeScore);

    String description();

}
