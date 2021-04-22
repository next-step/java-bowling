package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int LAST_ROUND = 10;

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

        if (!hasRemainTurn(frameNumber) && frameNumber < LAST_ROUND) {
            frames.add(frames.get(frameNumber - 1).nextFrame(frameNumber));
        }
    }

    public boolean hasRemainTurn(int frameNumber) {
        return frames.get(frameNumber - 1).hasNext();
    }
}
