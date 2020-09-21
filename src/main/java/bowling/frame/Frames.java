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

    public int getFrameNumber() {
        return frames.get(getCurrentFrame()).getFrameNumber();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public int getCurrentFrame() {
        return this.frames.size() - NUMBER_ONE;
    }

    public boolean isFinish() {
        return frames.get(getCurrentFrame()).isFinal();
    }

}
