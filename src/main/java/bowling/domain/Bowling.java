package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class Bowling {

    private Frames frames;

    public Bowling() {
        frames = new Frames();
    }

    public int addPlayerScore(int frameCount, int inputScore) {
        Frame frame = frames.addFrameScore(frameCount, inputScore);
        return frame.moveNextFrame();
    }

    public Frame getFrameResult(int frameCount) {
        return frames.getFrames().get(frameCount);
    }
}
