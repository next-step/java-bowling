package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private final List<FrameStrategy> frames;

    public Frames() {
        frames = new ArrayList<>();
        frames.add(new NormalFrame());
    }

    public List<FrameStrategy> getFrames() {
        return frames;
    }

    public void proceedRound(int frameNumber, PinNumber pinNumber) {
        frames.get(frameNumber - 1).play(pinNumber);

        if (!hasRemainTurn(frameNumber) && frameNumber <= 9) {
            frames.add(frames.get(frameNumber - 1).nextFrame(frameNumber));
        }
    }

    public boolean hasRemainTurn(int frameNumber) {
        return frames.get(frameNumber - 1).hasNext();
    }
}
