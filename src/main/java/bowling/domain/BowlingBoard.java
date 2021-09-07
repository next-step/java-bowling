package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class BowlingBoard {

    private final List<Frame> frames;

    private BowlingBoard(List<Frame> frames) {
        this.frames = frames;
    }

    public static BowlingBoard create() {
        return new BowlingBoard(new ArrayList<>());
    }

    public void write(Frame frame) {
        frames.add(frame);
    }

    public Frame get(int i) {
        return frames.get(i);
    }
}
