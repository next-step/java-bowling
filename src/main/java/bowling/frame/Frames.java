package bowling.frame;

import bowling.Pins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();

    private Frames() {
        frames.add(NormalFrame.first());
    }

    public static Frames first() {
        return new Frames();
    }

    public void bowl(Pins fallenPins) {
        Frame recentFrame = recentFrame();
        Frame resultFrame = recentFrame.bowl(fallenPins);
        if (recentFrame.isEnd() && !resultFrame.isEnd()) {
            frames.add(resultFrame);
        }
    }

    public int recentFrameNo() {
        return recentFrame().getFrameNo();
    }

    public boolean hasNextBowl() {
        return !(recentFrame().isEnd() && recentFrameNo() == Frame.MAX_FRAME_NO);
    }

    private Frame recentFrame() {
        return frames.get(frames.size() - 1);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public boolean isEnd() {
        return recentFrame().isEnd();
    }

    public int size() {
        return frames.size();
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

    @Override
    public String toString() {
        return "Frames{" +
                "frames=" + frames +
                '}';
    }
}
