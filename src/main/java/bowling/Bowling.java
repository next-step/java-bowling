package bowling;

import java.util.ArrayList;
import java.util.List;

public class Bowling {
    private List<NormalFrame> frames;

    public Bowling() {
        this.frames = new ArrayList<>();
    }

    public List<String> roll(int fallenPinCount) {
        if (frames.isEmpty()) {
            frames.add(new NormalFrame(1));
        }

        NormalFrame currentFrame = frames.get(frames.size() - 1);
        NormalFrame nextFrame = currentFrame.bowl(fallenPinCount);
        if (nextFrame.getNumber() != currentFrame.getNumber()) {
            frames.add(nextFrame);
        }

        return createResult();
    }

    public List<String> createResult() {
        List<String> result = new ArrayList<>();
        for (NormalFrame frame : frames) {
            result.add(frame.getRecord());
        }

        return result;
    }
}
