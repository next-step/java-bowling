package bowling.domain.state.running;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.exception.state.RunningCreateScoreException;

public class FirstBowl implements State {

    private final Pin pin;

    public FirstBowl(Pin pin) {
        this.pin = pin;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Score createScore() {
        throw new RunningCreateScoreException();
    }

    @Override
    public State bowl(Pin pin) {
        return null;
    }

}
