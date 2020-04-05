package bowling.domain.state;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Score;

public class Finished implements State {
    protected Frame frame;

    @Override
    public State bowl(Pin pins) {
        throw new IllegalArgumentException("게임이 종료되었습니다.");
    }

    @Override
    public boolean isFinish() {
        return true;
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
