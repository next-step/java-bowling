package bowling.domain;

import java.util.LinkedList;

public class Bowling {

    private LinkedList<Frame> defaultFrames;
    private int frameNumber;

    public Bowling() {
        defaultFrames = new LinkedList<>();
        frameNumber = 0;
    }

    public void bowl(int hit) {
        if (size() == 0) {
            createFrame(hit);
            return;
        }

        NormalFrame frame = getRecentFrame();
        if (frame.getFrameStatus() instanceof Strike) {
            createFrame(hit);
            return;
        }

        if (frame.size() < 2) {
            frame.bowl(hit);
            return;
        }

        createFrame(hit);
    }

    private void createFrame(int hit) {
        NormalFrame normalFrame = new NormalFrame(++frameNumber);
        normalFrame.bowl(hit);
        defaultFrames.push(normalFrame);
    }

    private NormalFrame getRecentFrame() {
        return (NormalFrame) defaultFrames.getFirst();
    }

    private int size() {
        return defaultFrames.size();
    }

    public LinkedList<Frame> getDefaultFrames() {
        return new LinkedList<>(defaultFrames);
    }
}
