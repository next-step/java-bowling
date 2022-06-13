package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.exception.BowlingException;
import bowling.exception.BowlingExceptionCode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private static final int START = 1;
    private static final int END = 10;

    private final List<Frame> frames;

    public static Board init() {
        List<Frame> frames = IntStream.rangeClosed(START, END-1)
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
        if (index > 10) {
            return new NormalFrame(11);
        }
        return frames.stream()
                .filter(frame -> frame.equal(index))
                .findFirst()
                .orElseThrow(() -> new BowlingException(BowlingExceptionCode.INVALID_FRAME_INDEX, String.valueOf(index)));
    }

    @Override
    public String toString() {
        return "Board{" +
                "frames=" + frames +
                '}';
    }
}
