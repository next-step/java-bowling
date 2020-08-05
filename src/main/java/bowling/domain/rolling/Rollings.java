package bowling.domain.rolling;

import bowling.domain.frame.Score;
import bowling.domain.state.StateFormat;

import java.util.List;

public interface Rollings {
    void roll(int pinCount);
    boolean isRollingPossible();
    boolean isState(StateFormat stateFormat);
    List<String> getStates();
    int calculateScore();
    void calculateAdditionalScore(Score score);
}
