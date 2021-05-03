package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private List<Frame> frames;

    public BowlingGame() {
        frames = new ArrayList<>();
        frames.add(new NormalFrame());
    }

    public boolean isAvailable() {
        return getCurrentFrame().isAvailable();
    }

    public int bowling(String inputScore) {
        return getCurrentFrame().addScore(Integer.parseInt(inputScore));
    }

    public int nextFrameIfAvailable() {
        if (!getCurrentFrame().isAvailable() && !(getCurrentFrame() instanceof FinalFrame)) {
            startNewFrame();
        }
        return frames.size();
    }

    private void startNewFrame() {
        frames.add(getCurrentFrame().createFrame(frames.size() + 1));
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }
}
