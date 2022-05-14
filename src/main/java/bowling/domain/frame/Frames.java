package bowling.domain.frame;

import bowling.exception.OutOfIndexException;
import bowling.domain.Pins;

import java.util.List;

public class Frames {

    public static final int START_FRAME_INDEX = 0;
    public static final int FINISH_FRAME_INDEX = 10;

    private int currentIndex;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
        this.currentIndex = START_FRAME_INDEX;
    }

    public static Frames create(List<Frame> frames) {
        return new Frames(frames);
    }

    public boolean isRunning() {
        return currentIndex < FINISH_FRAME_INDEX;
    }

    public void pitch(Pins pins) {
        Frame currentFrame = frames.get(currentIndex);
        currentFrame.pitch(pins);

        if (currentFrame.isFrameEnd()) {
            nextIndex();
        }
    }

    private void nextIndex() {
        int nextIndex = currentIndex + 1;
        if (nextIndex > FINISH_FRAME_INDEX) {
            throw new OutOfIndexException(nextIndex);
        }
        this.currentIndex += 1;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int getCurrentRound() {
        return currentIndex + 1;
    }

}
