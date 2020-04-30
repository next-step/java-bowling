package bowling;

import java.util.ArrayList;
import java.util.List;

public class Bowling {
    private List<Frame> frames;

    public Bowling() {
        this.frames = new ArrayList<>();
    }

    public List<String> roll(int fallenPinCount) {
        if (frames.isEmpty()) {
            frames.add(new Frame(1));
        }

        Frame currentFrame = frames.get(frames.size() - 1);
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
}
