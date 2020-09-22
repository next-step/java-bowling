package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int FINAL_FRAME_NO = 10;

    private final List<Frame> frames;

    public Frames() {
        frames = new ArrayList<>();
    }

    public int getRound() {
        return frames.size() + 1;
    }

    public void swing(int score) {
        Frame lastFrame = lastFrame();
        lastFrame.swing(score);
    }

    private Frame lastFrame() {

        if (frames.isEmpty()) {
            frames.add(new Frame());
        }

        return frames.get(frames.size() - 1);
    }

    private void addFrame() {
        frames.add(lastFrame().nextFrame(isFinalFrame()));
    }

    private boolean isFinalFrame() {
        return frames.size() >= FINAL_FRAME_NO;
    }

    public int frameSize() {
        return frames.size();
    }
}
