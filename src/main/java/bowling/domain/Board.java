package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.exception.BowlingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static bowling.exception.BowlingExceptionCode.INVALID_FRAME_INDEX;

public class Board {
    private static final int START = 1;
    private static final int END = 10;

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

    public Frame frame(int index) {
        return frames.stream()
                .filter(frame -> frame.index() == index)
                .findFirst()
                .orElseThrow(() -> new BowlingException(INVALID_FRAME_INDEX, String.valueOf(index)));
    }

    public Optional<Frame> frame2(int index) {
        return frames.stream()
                .filter(frame -> frame.index() == index)
                .findFirst();
    }

    @Override
    public String toString() {
        return "Board{" +
                "frames=" + frames +
                '}';
    }
}
