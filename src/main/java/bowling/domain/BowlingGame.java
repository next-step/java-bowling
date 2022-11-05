package bowling.domain;

import java.util.List;

public class BowlingGame {

    public static final int SIZE_OF_FRAMES = 10;

    private final List<BowlingGameFrame> frames;

    public BowlingGame(List<BowlingGameFrame> frames) {
        validate(frames);
        this.frames = frames;
    }

    private void validate(List<BowlingGameFrame> frames) {
        if (frames.size() != SIZE_OF_FRAMES) {
            throw new IllegalArgumentException(String.format("프레임은 %d개로 구성되어 있어야 합니다.", SIZE_OF_FRAMES));
        }
    }

}
