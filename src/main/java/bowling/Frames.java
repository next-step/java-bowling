package bowling;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    public static final int LAST_FRAME = 10;
    private List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public Frames() {
        this.frames = new ArrayList<>();
    }

    public Frame nextFrame() {
        return frames.stream()
                .filter(Frame::isAddAble)
                .findFirst()
                .orElse(createFrame());
    }

    private Frame createFrame() {
        Frame frame = new Frame(frames.size() + 1);
        frames.add(frame);
        return frame;
    }
}
