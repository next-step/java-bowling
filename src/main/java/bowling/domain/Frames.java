package bowling.domain;

import bowling.exception.CannotCreateException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Frames {
    private final static int MAX_LENGTH = 13;
    private List<Frame> frames = new ArrayList<>();

    public void add(Frame frame) throws CannotCreateException {
        if (frames.size() == MAX_LENGTH) {
            throw new CannotCreateException(CannotCreateException.MAX_LENGTH_FRAME);
        }
        this.frames.add(frame);
    }

    public Frame getFrame(int index) {
        return frames.get(index);
    }

    public Stream stream() {
        return frames.stream();
    }

    public FrameStatus status() {
        return last().status();
    }

    public Frame last() {
        return frames.get(frames.size() - 1);
    }

    public int lastIndex() {
        return frames.size() + 1;
    }

    public boolean isEnd() {
        if (frames.size() == 0) {
            return false;
        }
        if (FrameStatus.END == status()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return frames.toString();
    }

    public void endGame() {

    }
}
