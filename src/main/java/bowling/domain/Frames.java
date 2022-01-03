package bowling.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Frames {

    private static final int LAST_FRAMES_SIZE = 10;

    private final Deque<Frame> frames;

    private Frames(Deque<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        return new Frames(new ArrayDeque<>(Collections.singletonList(NormalFrame.first())));
    }

    public Frames bowl(Pins pins) {
        Frame frame = frames.removeLast().bowl(pins);
        frames.addLast(frame);

        if (frame.isFinished() && !this.isFinished()) {
            frames.addLast(frame.nextFrame());
        }

        return this;
    }

    public boolean isFinished() {
        return frames.size() == LAST_FRAMES_SIZE && frames.peekLast().isFinished();
    }

    public Round currentRound() {
        return frames.peekLast().round();
    }

    public List<Frame> frames() {
        return new ArrayList(frames);
    }

}
