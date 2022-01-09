package bowling.domain.frame;

import bowling.domain.Pins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();

    private Frames() {
        this.frames.add(NormalFrame.first());
    }

    public static Frames first() {
        return new Frames();
    }

    public int lastFrameIndex() {
        return frames.get(frames.size() - 1).getIndex();
    }

    public void bowl(Pins pins) {
        Frame recentFrame = recentFrame();
        Frame resultFrame = recentFrame.bowl(pins);
        if (isPossibleToCreate(recentFrame, resultFrame)) {
            frames.add(resultFrame);
        }
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public boolean hasNextPitching() {
        return !(recentFrame().isEnd() && frames.size() == FrameIndex.MAX_INDEX);
    }

    private Frame recentFrame() {
        return frames.get(frames.size() - 1);
    }

    private boolean isPossibleToCreate(Frame recentFrame, Frame resultFrame) {
        return recentFrame.isEnd() && !resultFrame.isEnd();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return Objects.equals(getFrames(), frames1.getFrames());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrames());
    }
}
