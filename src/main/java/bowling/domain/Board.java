package bowling.domain;

import bowling.exception.BowlingException;
import bowling.exception.BowlingExceptionCode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private static final int START = 1;
    private static final int END = 10;

    private final List<NormalFrame> frames;

    public static Board init() {
        return new Board(
                IntStream.rangeClosed(START, END)
                        .mapToObj(NormalFrame::new)
                        .collect(Collectors.toList())
        );
    }

    private Board(List<NormalFrame> frames) {
        this.frames = frames;
    }

    public List<NormalFrame> frames() {
        return frames;
    }

    public void addScore(int frameIndex, int score) {
        frame(frameIndex).addScore(score);
    }

    public NormalFrame frame(int index) {
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
