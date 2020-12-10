package bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private final List<Frame> frames;

    public BowlingGame() {
        frames = new ArrayList<>();
    }

    public void initFrames() {
        for (int i = 1; i <= 9; i++) {
            frames.add(new NormalFrame(i));
        }

        frames.add(new LastFrame(10));
    }

    public int size() {
        return frames.size();
    }
}
