package bowling.domain.frame;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Frames {
    private final List<Frame> frames;
    private Frame current;

    public Frames() {
        this.frames = createFrames();
    }

    public int bowl(int pins) {
        current = current.bowl(pins);
        return current.number();
    }

    public int currentFrameNumber() {
        return current.number();
    }

    private List<Frame> createFrames() {
        List<Frame> newFrames = new LinkedList<>();
        NormalFrame frame = NormalFrame.first();
        newFrames.add(frame);

        for (int index = 1; index <= 8; index++) {
            frame = frame.next();
            newFrames.add(frame);
        }
        newFrames.add(frame.last());

        current = newFrames.get(0);
        return newFrames;
    }

    public boolean isGameEnd() {
        return current.isGameEnd();
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(frames);
    }
}
