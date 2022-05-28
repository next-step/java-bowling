package bowling.domain.state;

import bowling.domain.Score;

public interface State {
    State bowl(int pins);
    Score score();
    Score sumBeforeScore(Score beforeScore);
    boolean finish();
    boolean canCalculate(Score beforeScore);
    String mark();
}
