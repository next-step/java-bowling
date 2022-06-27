package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.exception.BowlingException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.exception.BowlingExceptionCode.INVALID_FRAME_INDEX;

public class Board {
    private static final int START = 1;
    private static final int END = 10;

    private final List<Frame> frames;

    public static Board init() {
        List<Frame> frames = IntStream.rangeClosed(START, END - 1)
                .mapToObj(NormalFrame::new)
                .collect(Collectors.toList());
        frames.add(new FinalFrame(END));
        return new Board(frames);
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
