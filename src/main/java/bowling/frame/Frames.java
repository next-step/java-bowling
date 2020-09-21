package bowling.frame;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static bowling.global.utils.CommonConstant.NUMBER_ONE;

public class Frames {

    private List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public void saveScore(Frame frame) {
        this.frames.add(frame);
    }

    public static Frames init() {
        Frame frame = NormalFrame.first();
        List<Frame> frames = new LinkedList<>();
        frames.add(frame);
        return new Frames(frames);
    }

    public Frame pitch(String inputScore) {
        Frame frame = getCurrentFrame();
        frame.pitch(inputScore);
        return frame;
    }

    public Frame next() {
        return getCurrentFrame().next();
    }

    public int getFrameNumber() {
        return frames.get(currentFrameIndex()).getFrameNumber();
    }

    public boolean canPitching() {
        return getCurrentFrame().canPitching();
    }

    public boolean isStrikeIgnore() {
        return getCurrentFrame().isStrikeIgnore();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    private int currentFrameIndex() {
        return this.frames.size() - NUMBER_ONE;
    }

    public Frame getCurrentFrame() {
        return frames.get(currentFrameIndex());
    }

    public boolean isFinish() {
        return frames.get(currentFrameIndex()).isFinal();
    }

}
