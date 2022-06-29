package bowling.domain;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Frame> frames;

    public static Board init() {
        return new Board(new ArrayList<>());
    }

    private Board(List<Frame> frames) {
        this.frames = frames;
    }

    public List<Frame> frames() {
        return frames;
    }

    public void addFrameIfAbsent(Frame newFrame) {
        if (absent(newFrame)) {
            frames.add(newFrame);
        }
    }

    private boolean absent(Frame newFrame) {
        return frames.stream()
                .mapToInt(Frame::index)
                .noneMatch(index -> index == newFrame.index());
    }

    @Override
    public String toString() {
        return "Board{" +
                "frames=" + frames +
                '}';
    }
}
