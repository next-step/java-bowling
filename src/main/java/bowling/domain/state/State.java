package bowling.domain.state;

import bowling.domain.PitchResults;
import bowling.domain.Score;

public interface State {

    boolean isFinish();

    State pitch(int knockedDownPins);

    Score createScore(int previousScore);

    int sumUpCurrentResult();

    PitchResults getPitchResults();

    int getPitchTryCount();
}
