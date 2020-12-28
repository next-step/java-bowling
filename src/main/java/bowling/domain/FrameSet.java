package bowling.domain;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class FrameSet {

    private List<Frame> frames;

    private FrameSet(List<Frame> frames) {
        this.frames = frames;
    }

    public static FrameSet of(List<Frame> frames) {
        return new FrameSet(frames);
    }

    public boolean isEnd() {
        return false;
    }

    public Iterator<Frame> frames() {
        return frames.iterator();
    }

    public void playForEachFrame(Consumer<Frame> frameAction) {
        frames.iterator()
                .forEachRemaining(frameAction);
    }

}

