package bowling.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created : 2020-12-16 오전 8:58
 * Developer : Seo
 */
public class Frames {
    public static final int ALL_FRAMES = 10;

    private final List<Frame> frames;

    public Frames() {
        this.frames = new LinkedList<>();
        init();
    }

    private void init() {
        Frame frame = new Frame(Frame.INIT);
        frames.add(frame);
        while (frame.getFrameNo() < ALL_FRAMES) {
            Frame afterFirstBowl = firstBowl(frame);
            Frame nextFrame = secondBowl(frame, afterFirstBowl);
            frames.add(nextFrame);
            frame = nextFrame;
        }
    }

    private Frame firstBowl(Frame frame) {
        return frame.bowl(Bowling.stroke(Frame.ALL_PINS));
    }

    private Frame secondBowl(Frame frame, Frame afterFirstBowl) {
        if (frame.equals(afterFirstBowl)) {
            return frame.bowl(Bowling.stroke(afterFirstBowl.remainPins()));
        }
        return afterFirstBowl;
    }

    public int size() {
        return frames.size();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public Frame get(int i) {
        return frames.get(i);
    }
}
