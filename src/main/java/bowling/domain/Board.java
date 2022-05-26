package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private static final int START = 1;
    private static final int END = 10;

    private final List<Frame> frames;

    public static Board init() {
        return new Board(
                IntStream.rangeClosed(START, END)
                        .mapToObj(Frame::new)
                        .collect(Collectors.toList())
        );
    }

    private Board(List<Frame> frames) {
        this.frames = frames;
    }

    public List<Frame> frames() {
        return frames;
    }
}
