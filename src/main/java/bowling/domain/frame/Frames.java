package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.exception.FrameSizeException;

import java.util.ArrayList;
import java.util.List;

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
        for (int i = 0; i < FRAME_SIZE - 1; i++) {
            frame = frame.createNextFrame(false);
            frames.add(frame);
        }
        frame = frame.createNextFrame(true);
        frames.add(frame);

        validFrame(frames);
        return of(frames);

    }

    private static void validFrame(List<Frame> frames) {
        if (frames.size() != FRAME_SIZE) {
            throw new FrameSizeException();
        }
    }

    public void pitch(Point point) {
        Frame frame = this.frames.get(currentFramePosition);
        frame.pitch(point);

        if (!frame.hasScoreTurn()) {
            currentFramePosition++;
        }
    }

    public List<FrameResultDto> getFrameResultDtos() {
        return frames.stream()
                .map(Frame::getFrameResultDto)
                .collect(toList());
    }

    public int getCurrentFramePosition() {
        return currentFramePosition;
    }

    public boolean isFinished() {
        return !this.frames.get(LAST_FRAME_INDEX).hasScoreTurn();
    }
}
