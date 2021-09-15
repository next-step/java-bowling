package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Frames {
    public static final int FIRST_FRAME_INDEX = 1;
    public static final int LAST_FRAME_INDEX = 9;

    private List<Frame> frames = new ArrayList<>();

    public Frames() {
        frames.add(new NormalFrame(FIRST_FRAME_INDEX));
    }

    public int size() {
        return frames.size();
    }

    public Frame getLast() {
        return frames.get(LAST_FRAME_INDEX);
    }

    public Frame get(int index) {
        return frames.get(index);
    }

    public void add(final Frame next) {
        frames.add(next);
    }

    public List<Frame> value() {
        return frames;
    }

}
