package bowling.domain.state.running;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.exception.message.ErrorMessage;

import java.util.Collections;
import java.util.List;

public abstract class Running extends State {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public boolean isCleanState() {
        return false;
    }

    @Override
    public Pins getFirstPins() {
        throw new IllegalArgumentException(ErrorMessage.NOT_ALLOW_FIRST_BOWL);
    }

    @Override
    public Pins getSecondPins() {
        throw new IllegalArgumentException(ErrorMessage.NOT_ALLOW_SECOND_BOWL);
    }

    @Override
    public List<State> getState() {
        return Collections.singletonList(this);
    }

    @Override
    public Score getScore() {
        return Score.UN_SCORE;
    }
}
