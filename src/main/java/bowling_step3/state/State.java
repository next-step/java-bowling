package bowling_step3.state;

import bowling_step3.domain.Scores;

public interface State {
    State pitch(int i);
}
