package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final int LAST_FRAME = 10;

    private final String name;

    private boolean isDone;

    private List<Frame> frames = new ArrayList<>();

    public Player(String name) {
        this.name = name;
        frames.add(Frame.first());
    }

    public void addPinCounts(int pintCount) {
        Frame frame = frames.get(frames.size() - 1);
        frame.addPinCounts(pintCount);
        if (frame.isDone()) {
            if (frame.number() == LAST_FRAME) {
                isDone = true;
            } else {
                frames.add(frame.next());
            }

        }
    }

    public int currentFrameNumber() {
        return frames.get(frames.size() - 1).number();
    }

    public String name() {
        return name;
    }

    public List<Frame> frames() {
        return frames;
    }

    public boolean isDone() {
        return isDone;
    }
}
