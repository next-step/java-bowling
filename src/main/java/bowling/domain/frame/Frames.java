package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.exception.FrameSizeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class Frames {
    public static final int FRAME_SIZE = 10;
    public static final int LAST_FRAME_INDEX = 9;

    private final List<Frame> frames;
    private int currentFramePosition;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    private static Frames of(List<Frame> frames) {
        return new Frames(frames);
    }

    public static Frames init() {
        List<Frame> frames = new ArrayList<>();
        Frame frame = BasicFrame.initFirst();

        while (frame instanceof BasicFrame) {
            frame = frame.createNextFrame();
            frames.add(frame);
        }


        validFrame(frames);
        return of(frames);

    }

    private static void validFrame(List<Frame> frames) {
        if (frames.size() != FRAME_SIZE) {
            throw new FrameSizeException(frames.size());
        }
    }

    public void pitch(Point point) {
        Frame frame = this.frames.get(currentFramePosition);
        frame.pitch(point);

        if (!frame.hasScoreTurn()) {
            currentFramePosition++;
        }
    }

    public int getCurrentFramePosition() {
        return currentFramePosition;
    }

    public boolean isFinished() {
        return !this.frames.get(LAST_FRAME_INDEX).hasScoreTurn();
    }

    public List<FrameResultDto> getFrameResultDtos() {
        return frames.stream()
                .map(Frame::getFrameResultDto)
                .collect(toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return currentFramePosition == frames1.currentFramePosition && Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames, currentFramePosition);
    }
}
