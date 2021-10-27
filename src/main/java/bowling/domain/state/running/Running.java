package bowling.domain.state.running;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.exception.state.RunningCreateScoreException;

public abstract class Running implements State {

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Score createScore() {
        throw new RunningCreateScoreException();
    }

}
