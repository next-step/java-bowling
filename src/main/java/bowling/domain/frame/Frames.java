package bowling.domain.frame;

import java.util.LinkedList;

public class Frames {

    private LinkedList<Frame> frames;

    public Frames(LinkedList<Frame> frames) {
        this.frames = frames;
    }

    public void bowl(int pins) {
        Frame preFrame = frames.getLast();
        if (preFrame.isFinish()) {
            Frame frame = new Frame(preFrame.getFrameNumber() + 1);
            frame.bowl(pins);
            frames.addLast(frame);
            return;
        }
        preFrame.bowl(pins);
    }

    public LinkedList<Frame> getFrames() {
        return new LinkedList<>(frames);
    }
}
