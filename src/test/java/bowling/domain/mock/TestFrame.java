package bowling.domain.mock;

import bowling.domain.frames.Frame;
import bowling.domain.frames.NormalFrame;

public class TestFrame extends NormalFrame {
    public TestFrame(int index, Frame previousFrame) {
        super(index, previousFrame);
    }

    public static TestFrame getFirstFrame() {
        return new TestFrame(1, null);
    }

    public static TestFrame getFrame(int index) {
        return new TestFrame(index, null);
    }

    public Integer getCalculatedScore() {
        return super.calculateScore();
    }
}
