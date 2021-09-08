package step2.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private List<Frame> frames = new ArrayList<>();

    public void startNormalFrame() {
        frames.add(new NormalFrame());
    }

    public void startFinalFrame() {
        frames.add(new FinalFrame());
    }

    public void throwBall(int numOfPin) {
        int frameIndex = frames.size() - 1;
        if (frameIndex >= 0) {
            frames.get(frameIndex).knockDown(numOfPin);
            return;
        }
    }

    public List<Integer> frameScoreInfo(int frameRound) {
        Frame frame = frames.get(frameRound - 1);
        return frame.scoreInfo();
    }

    public boolean isFinish(int frameRound) {
        Frame frame = frames.get(frameRound - 1);
        return frame.isFinish();
    }
}
