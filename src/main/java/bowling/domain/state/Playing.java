package bowling.domain.state;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Score;

public class Playing implements State {
    protected Frame frame;

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
        return null;
    }

    @Override
    public void renewScore(Score score) {

    }

    @Override
    public void frame(int frameNumber) {
        frame = new Frame(frameNumber);
    }

    @Override
    public Frame getFrame() {
        return frame;
    }

}
