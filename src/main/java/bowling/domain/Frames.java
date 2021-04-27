package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class Frames {
    private final Map<Integer, Frame> frames;

    public Frames() {
        this.frames = new HashMap<>();
    }

    public Frame get(int frameNo) {
        return frames.get(frameNo);
    }

    public Map<Integer, Frame> frames() {
        return frames;
    }

    public void pitch(int frameNo, int point) {
        Frame frame = new Frame(frameNo);
        if (frames.containsKey(frameNo)) {
            frame = frames.get(frameNo);
        }
        frames.put(frameNo, frame.pitch(point));
    }

    public boolean contains(int frameNo) {
        return frames.containsKey(frameNo);
    }
}
