package bowling.domain.state.running;

import bowling.domain.score.Score;
import bowling.domain.state.State;

public interface Running extends State {

    default boolean isFrameEnd() {
        return false;
    }

    default Score score() {
        return Score.unavailable();
    }

}
