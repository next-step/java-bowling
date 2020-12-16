package bowling.domain.frames;

import bowling.domain.KnockDownPins;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Frames implements FramesService, FramesViewDto {
    public static final int MAX_FRAME_SIZE = 10;
    private final List<Frame> value;
    private int currentIndex;

    private Frames() {
        value = initFrames();
        currentIndex = 0;
    }

    public static Frames init() {
        return new Frames();
    }

    private List<Frame> initFrames() {
        List<Frame> frames = new ArrayList<>();
        Frame frame = NormalFrame.getFirstFrame();
        frames.add(frame);
        while (frames.size() < MAX_FRAME_SIZE) {
            frame = frame.initNextFrame();
            frames.add(frame);
        }
        return frames;
    }

    public int size() {
        return value.size();
    }

    @Override
    public boolean isEnd() {
        Frame lastFrame = value.get(MAX_FRAME_SIZE - 1);
        return lastFrame.isEnd();
    }

    @Override
    public void setKnockDownPins(KnockDownPins knockDownPins) {
        Frame currentFrame = value.get(currentIndex);
        currentFrame.setKnockDownPins(knockDownPins);
        if (currentFrame.isEnd()) {
            currentIndex++;
        }
    }

    @Override
    public int getCurrentFrameIndex() {
        return currentIndex + 1;
    }

    @Override
    public Stream<FrameViewDto> viewDtoStream() {
        return value.stream()
                .map(frame -> frame);
    }
}
