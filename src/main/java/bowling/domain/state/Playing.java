package bowling.domain.state;

import bowling.domain.frame.Frame2;
import bowling.domain.frame.Score;

public class Playing implements State {

    @Override
    public State bowl(int pins) {
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
        return null;
    }

    @Override
    public void renewScore(Score score) {

    }

    @Override
    public Frame2 frame(int frameNumber) {
        return new Frame2(frameNumber);
    }


}
