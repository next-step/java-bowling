package bowling;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames start(Pin falledPins) {
        List<Frame> frames = new ArrayList<>();
        NormalFrame firstFrame = new NormalFrame(1, falledPins);
        frames.add(firstFrame);
        return new Frames(frames);
    }

    public void bowl(Pin falledPins) {
        Frame lastFrame = getLastFrame();
        if (lastFrame.isFinished()) {
            frames.add(lastFrame.nextFrame(falledPins));
            return;
        }
        lastFrame.bowl(falledPins);
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }
}
