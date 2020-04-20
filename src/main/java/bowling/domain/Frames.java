package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Frames {
    private static final int MAX_FRAMES = 10;

    private final List<Frame> frames;

    Frames() {
        this.frames = new ArrayList<>();
        frames.add(Frame.init());
    }

    int getCurrentFrameNumber() {
        if (isCurrentFrameDone()) {
            return frames.size() + 1;
        }
        return frames.size();
    }

    private boolean isCurrentFrameDone() {
        return getLast().isFrameSet();
    }

    private Frame getLast() {
        return frames.get(frames.size() - 1);
    }

    void shot(int shot) {
        if (isCurrentFrameDone()) {
            frames.add(getNextFrame(shot));
            return;
        }

        getLast().shot(shot);
    }

    private Frame getNextFrame(int shot) {
        if (frames.size() < MAX_FRAMES - 1) {
            return getLast().next(shot);
        }
        return getLast().last(shot);
    }

    boolean isGameSet() {
        return frames.stream()
                .filter(Frame::isFrameSet)
                .count() == MAX_FRAMES;
    }

    public Stream<Frame> stream() {
        return frames.stream();
    }
}
