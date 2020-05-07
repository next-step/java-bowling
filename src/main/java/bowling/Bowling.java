package bowling;

import java.util.ArrayList;
import java.util.List;

public class Bowling {
    private List<Frame> frames = new ArrayList<>();

    public Bowling() {
        this.frames.add(new NormalFrame(1));
    }

    public List<String> roll(int fallenPinCount) {
        Frame currentFrame = frames.get(frames.size() - 1);
        if (currentFrame instanceof LastFrame) {
            if (!((LastFrame) currentFrame).isGameEnd()) {
                currentFrame.bowl(fallenPinCount);
            }
            return createResult();
        }
        Frame nextFrame = currentFrame.bowl(fallenPinCount);
        if (nextFrame.getNumber() != currentFrame.getNumber()) {
            frames.add(nextFrame);
        }

        return createResult();
    }

    public List<String> createResult() {
        List<String> result = new ArrayList<>();
        for (Frame frame : frames) {
            result.add(frame.getRecord());
        }

        return result;
    }

    public int getCurrentFrameNumber() {
        return frames.size();
    }
}
