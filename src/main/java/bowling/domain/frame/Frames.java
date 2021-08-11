package bowling.domain.frame;

import bowling.domain.score.TurnScore;

import java.util.*;

public final class Frames implements Iterable<Frame> {
    public static final int MAX_FRAME_NUMBER = 10;

    private final LinkedList<Frame> frames;

    public Frames() {
        frames = new LinkedList<>();
        frames.add(Frame.firstFrame());
    }

    public void bowl(final TurnScore score) {
        inProgressFrame().bowl(score);
    }

    private Frame inProgressFrame() {
        Frame lastFrame = frames.getLast();

        if (lastFrame.isCompleted()) {
            frames.add(
                    lastFrame.newNextFrame()
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
