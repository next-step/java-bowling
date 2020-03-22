package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.framestatus.Strike;

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
        defaultFrames.add(normalFrame);
    }

    private NormalFrame getRecentFrame() {
        return (NormalFrame) defaultFrames.getLast();
    }

    private int size() {
        return defaultFrames.size();
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public LinkedList<Frame> getDefaultFrames() {
        return new LinkedList<>(defaultFrames);
    }
}
