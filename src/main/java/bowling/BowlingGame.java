package bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    public static final int MAX_FRAME_SIZE = 10;
    private final List<Frame> frames;
    private Frame currentFrame;

    private BowlingGame() {
        frames = new ArrayList<>();
        initFrames();
    }

    public static BowlingGame init() {
        return new BowlingGame();
    }

    public void initFrames() {
        Frame frame = NormalFrame.getFirstFrame();
        currentFrame = frame;
        frames.add(frame);

        while (size() < MAX_FRAME_SIZE) {
            frame = frame.initNextFrame();
            frames.add(frame);
        }
    }

    public int size() {
        return frames.size();
    }

    public boolean isEnd() {
        return frames.get(MAX_FRAME_SIZE - 1).isEnd();
    }

    public void setKnockDownPins(int knockDownPins) {
        if (currentFrame.isEnd()) {
            currentFrame = currentFrame.getNextFrame();
        }

        currentFrame.setKnockDownPins(knockDownPins);
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
