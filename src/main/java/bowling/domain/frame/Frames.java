package bowling.domain.frame;

import bowling.domain.score.TurnScore;

import java.util.*;

public final class Frames implements Iterable<Frame> {
    private static final int START_FRAME_NUMBER = 1;
    public static final int MAX_FRAME_NUMBER = 10;

    private final LinkedList<Frame> frames;

    public Frames() {
        frames = new LinkedList<>();
        frames.add(
                new Frame(START_FRAME_NUMBER)
        );
    }

    public void bowl(final TurnScore score) {
        inProgressFrame().bowl(score);
    }

    private Frame inProgressFrame() {
        Frame lastFrame = frames.getLast();

        if (lastFrame.isCompleted()) {
            frames.add(
                    lastFrame.nextFrame()
            );
            return frames.getLast();
        }
        return lastFrame;
    }

    public boolean isCompleted() {
        return frames.size() == MAX_FRAME_NUMBER
                && frames.getLast().isCompleted();
    }

    public int currentFrameNumber() {
        return inProgressFrame().currentFrameNumber();
    }

    public int size() {
        return frames.size();
    }

    @Override
    public Iterator<Frame> iterator() {
        return frames.iterator();
    }
}
