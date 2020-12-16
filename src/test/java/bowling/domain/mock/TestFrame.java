package bowling.domain.mock;

import bowling.domain.frames.FrameImpl;
import bowling.domain.frames.NormalFrame;

import java.util.Optional;

public class TestFrame extends NormalFrame {
    public TestFrame(int index, FrameImpl previousFrame) {
        super(index, previousFrame);
    }

    public static TestFrame getFirstFrame() {
        return new TestFrame(1, null);
    }

    public static TestFrame getFrame(int index) {
        return new TestFrame(index, null);
    }

    public Optional<Integer> getCalculatedScore() {
        return super.getScore();
    }
}
