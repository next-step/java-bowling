package bowling;

import java.util.ArrayList;
import java.util.List;

public class Bowling {
    private static final int LAST_FRAME_NUMBER = 10;

    private List<Frame> frames = new ArrayList<>();

    public Bowling() {
        this.frames.add(new NormalFrame(1));
    }

    public List<String> roll(int fallenPinCount) {
        if (isLastFrame()) {
            LastFrame lastFrame = (LastFrame) frames.get(9);
            lastFrame.bowl(fallenPinCount);

            return createResult();
        }

        Frame currentFrame = frames.get(frames.size() - 1);
        Frame nextFrame = currentFrame.bowl(fallenPinCount);
        if (nextFrame.getNumber() != currentFrame.getNumber()) {
            frames.add(nextFrame);
        }

        return createResult();
    }

    private boolean isLastFrame() {
        return frames.size() == LAST_FRAME_NUMBER;
    }

    public List<String> createResult() {
        List<String> result = new ArrayList<>();
        for (Frame frame : frames) {
            result.add(frame.getRecord());
        }

        return result;
    }

    public boolean isGameEnd() {
        if (isLastFrame()) {
            LastFrame lastFrame = (LastFrame) frames.get(9);
            return lastFrame.isGameEnd();
        }
        return false;
    }

    public int getCurrentFrameNumber() {
        return frames.size();
    }
}
