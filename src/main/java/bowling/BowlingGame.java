package bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    public static final int MAX_FRAME_SIZE = 10;
    private final List<Frame> frames;

    public BowlingGame() {
        frames = new ArrayList<>();
    }

    public void initFrames() {
        for (int index = 1; index < MAX_FRAME_SIZE; index++) {
            frames.add(new NormalFrame(index));
        }

        frames.add(new LastFrame(MAX_FRAME_SIZE));
    }

    public int size() {
        return frames.size();
    }
}
