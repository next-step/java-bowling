package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    protected static final int BOWLING_FRAMES_DEFAULT = 10;
    private Frame head;
    private Frame current;
    private List<Frame> frames;
    private String name;

    public Frames() {
        this("");
    }

    public Frames(String name) {
        frames = new ArrayList<>();
        this.name = name;

        create();
    }

    public int throwBall(int hitCount) {
        current.shot(hitCount);

        if (current.isDone()) {
            current = current.next();
            return 1;
        }

        return 0;
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

    private void create() {
        Frame frame = Frame.of();

        head = frame;
        current = frame;
        frames.add(frame);
        for (int i = 1; i < BOWLING_FRAMES_DEFAULT; ++i) {
            frame = frame.createNext();
            frames.add(frame);
        }
    }

    public Frame getHead() {
        return head;
    }

    public boolean isEndGame() {
        return current == null;
    }
}
