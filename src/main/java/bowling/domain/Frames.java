package bowling.domain;

import java.util.Arrays;
import java.util.LinkedList;

public class Frames {
    public static final int MAX_INDEX = 10;

    private final LinkedList<Frame> frames;

    public Frames() {
        this.frames = new LinkedList<>();
        frames.add(new NormalFrame());
    }

    public Frames(Frame... frames) {
        this.frames = new LinkedList<>();
        this.frames.addAll(Arrays.asList(frames));
    }

    public LinkedList<Frame> getFrames() {
        return frames;
    }

    public void addRoll(Pin pin) {
        Frame last = frames.getLast();
        last.addRoll(pin);
        last.updateStatus();
    }

    public boolean isEnd() {
        return this.frames.getLast().isEnd();
    }

    public void add() {
        int lastIndex = frames.size();
        if (lastIndex < MAX_INDEX) {
            frames.add(FrameFactory.create(lastIndex));
        }
    }

    public Score calculateScore() {
        return this.frames.getLast().calculateScore();
    }
}
