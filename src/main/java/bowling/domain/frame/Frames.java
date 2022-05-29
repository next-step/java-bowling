package bowling.domain.frame;

import bowling.domain.Pins;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int MAX_FRAME_SIZE = 10;

    private final List<Frame> frames;

    public Frames(List<Frame> frames) {
        validateFrames(frames);
        this.frames = frames;
    }

    private void validateFrames(List<Frame> frames) {
        if (frames == null) {
            throw new IllegalArgumentException("프레임 리스트는 null 일 수 없습니다.");
        }
    }

    public static Frames initialize() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.initialize());
        return new Frames(frames);
    }

    public boolean isFinalFrameEnd() {
        return frames.size() == MAX_FRAME_SIZE && currentFrame().isFrameEnd();
    }

    public void bowl(Pins hitPins) {
        Frame currentFrame = currentFrame();
        Frame resultFrame = currentFrame().bowl(hitPins);
        if (currentFrame.isFrameEnd() && !currentFrame.isFinalFrame()) {
            frames.add(resultFrame);
        }
    }

    public int getCurrentFrameNumber() {
        return frames.size();
    }

    private Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    public List<Frame> frames() {
        return frames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frames frames1 = (Frames) o;

        return frames.equals(frames1.frames);
    }

    @Override
    public int hashCode() {
        return frames.hashCode();
    }
}