package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    protected static final int BOWLING_FRAMES_TOTAL = 12;
    private Frame head;
    private Frame current;
    private List<Frame> frames;
    private String name;

    public Frames() {
        this("");
    }

    public Frames(String name) {
        frames = new ArrayList<>(BOWLING_FRAMES_TOTAL);
        this.name = name;

        create();
    }

    public int play(int hitCount) {
        int remainedPin = 10;

        return remainedPin;
    }

    public Frame getCurrent() {
        return current;
    }

    public String getName() {
        return this.name;
    }

    public List<Frame> get() {
        return this.frames;
    }

    protected void create() {
        Frame frame = Frame.of();

        head = frame;
        current = frame;
        frames.add(frame);
        for (int i = 1; i < BOWLING_FRAMES_TOTAL; ++i) {
            frame = frame.createNext();
            frames.add(frame);
        }
    }

    public Frame getHead() {
        return head;
    }
}
