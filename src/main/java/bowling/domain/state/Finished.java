package bowling.domain.state;

import bowling.domain.score.Score;

public abstract class Finished implements State {

    @Override
    public boolean isEndedState() {
        return true;
    }

    abstract public Score createScore();
}
