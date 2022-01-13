package bowling.frame;

import bowling.Pins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();

    private Frames() {
        frames.add(NormalFrame.first());
    }

    public static Frames first() {
        return new Frames();
    }

    public void bowl(Pins fallenPins) {
        Frame recentFrame = recentFrame();
        Frame resultFrame = recentFrame.bowl(fallenPins);
        if (recentFrame.isEnd() && !resultFrame.isEnd()) {
            frames.add(resultFrame);
        }
    }

    public int recentFrameNo() {
        return recentFrame().getFrameNo();
    }

    public boolean hasNextBowl() {
        return !(recentFrame().isEnd() && recentFrameNo() == Frame.MAX_FRAME_NO);
    }

    private Frame recentFrame() {
        return frames.get(frames.size() - 1);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public int size() {
        return frames.size();
    }
}
