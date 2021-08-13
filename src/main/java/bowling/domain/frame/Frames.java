package bowling.domain.frame;

import bowling.domain.score.TurnScore;
import bowling.exception.InvalidFrameStateException;

import java.util.Iterator;
import java.util.LinkedList;

public final class Frames implements Iterable<Frame> {
    public static final int MAX_FRAME_NUMBER = 10;

    private final LinkedList<Frame> frames;

    public Frames() {
        frames = new LinkedList<>();
        frames.add(NormalFrame.firstFrame());
    }

    public void bowl(final TurnScore score) {
        inProgressFrame().bowl(score);
    }

    private Frame inProgressFrame() {
        Frame lastFrame = frames.getLast();

        if (lastFrame.isCompleted()) {
            frames.add(
                    newNextFrame(lastFrame)
            );
            return frames.getLast();
        }
        return lastFrame;
    }

    private Frame newNextFrame(Frame currentFrame) {
        if (!(currentFrame instanceof NormalFrame)) {
            throw new InvalidFrameStateException(InvalidFrameStateException.ErrorCode.NEXT_FRAME_FAILURE);
        }
        return ((NormalFrame) currentFrame).newNextFrame();
    }

    public boolean isCompleted() {
        return frames.size() == MAX_FRAME_NUMBER
                && frames.getLast().isCompleted();
    }

    public boolean isCompleted(int checkTurnNumber) {
        if (frames.size() <= checkTurnNumber) {
            return false;
        }
        return frames.get(checkTurnNumber).isCompleted();
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
