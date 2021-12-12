package bowling.domain.frame;

import bowling.domain.pin.Pin;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;

public class Frames {

    private static final int INDEX_UNIT = 1;
    
    public final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        return new Frames(new ArrayList<>(singletonList(FrameFactory.firstFrame())));
    }

    /**
     * @return 더 투구할 수 있는지
     */
    public boolean pitch(Pin pin) {
        Frame currentFrame = currentFrame();
        if (currentFrame.pitch(pin)) {
            return true;
        }
        if (currentFrame.hasNextFrame()) {
            addNextFrame();
            return true;
        }
        return false;
    }

    private Frame currentFrame() {
        return frames.get(frames.size() - INDEX_UNIT);
    }

    private void addNextFrame() {
        frames.add(currentFrame().next());
    }

    public int numberOfFrame() {
        return currentFrame().getNumber();
    }

    public List<Frame> frames() {
        return unmodifiableList(frames);
    }
}
