package bowling.domain.state.running;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.exception.state.RunningCreateScoreException;

public class FirstBowl implements State {

    private final Pin first;

    public FirstBowl(Pin first) {
        this.first = first;
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
        if (first.isSpare(pin)) {
            return new Spare();
        }
        return new Miss();
    }

}
