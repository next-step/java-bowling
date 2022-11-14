package bowling.domain;

import java.util.Arrays;
import java.util.LinkedList;

public class Frames {
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

    public boolean isEnd(int index) {
        boolean isEnd = this.frames.get(index).isEnd();
        if (isEnd) {
            frames.add(FrameFactory.create(index + 1));
        }
        return isEnd;
    }
}
