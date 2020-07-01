package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class Bowling {

    private Frames frames;

    public Bowling() {
        frames = new Frames();
    }

    public int addPlayerScore(int frameCount, int inputScore) {
        return frames.addFrameScore(frameCount, inputScore);
    }

    public Frame getFrameResult(int frameCount) {
        return frames.getFrames().get(frameCount);
    }

    public int getFrameScore(int frameCount) {
        return frames.getFrameScore(frameCount);
    }

}
