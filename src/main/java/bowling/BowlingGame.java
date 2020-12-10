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
        Frame frame = NormalFrame.getFirstFrame();
        frames.add(frame);

        while (size() < MAX_FRAME_SIZE) {
            frame = frame.getNextFrame();
            frames.add(frame);
        }
    }

    public int size() {
        return frames.size();
    }
}
