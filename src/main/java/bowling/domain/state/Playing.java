package bowling.domain.state;

import bowling.domain.frame.Score;

public class Playing implements State {

    @Override
    public State bowl(Pin pins) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String display() {
        return null;
    }

    @Override
    public Score getScore() {
        return null;
    }

    @Override
    public Score calculateByBeforeScore(Score before) {
        return before;
    }

    @Override
    public void updateScore(Score score) {

    }

}
