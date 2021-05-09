package bowling.domain.state;

import bowling.domain.score.Score;

public interface State {
    State bowl(Pins pins);

    boolean isFinish();

    Score score();

    Score calculateAdditionalScore(Score beforeScore);

    String description();
}
