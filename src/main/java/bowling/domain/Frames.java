package bowling.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Frames {
    private final Map<Integer, Frame> frames;

    public Frames() {
        this.frames = new HashMap<>();
    }

    public Frame get(int frameNo) {
        return frames.get(frameNo);
    }

    public Map<Integer, Frame> frames() {
        return Collections.unmodifiableMap(frames);
    }

    public void pitch(int frameNo, int point) {
        Frame frame = Optional.ofNullable(frames.get(frameNo))
                .orElse(new Frame(frameNo));

        frames.put(frameNo, frame.pitch(point));
    }

    public boolean contains(int frameNo) {
        return frames.containsKey(frameNo);
    }
}
