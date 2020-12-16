package bowling.domain.frames;

import bowling.domain.KnockDownPins;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Frames implements FramesService, FramesViewDto {
    public static final int MAX_FRAME_SIZE = 10;
    private final FirstAndLastFrame firstAndLastFrame;
    private Frame currentFrame;

    private Frames() {
        firstAndLastFrame = initFrames();
        currentFrame = firstAndLastFrame.getFirst();
    }

    public static Frames init() {
        return new Frames();
    }

    private FirstAndLastFrame initFrames() {
        Frame firstFrame = NormalFrame.getFirstFrame();
        return FirstAndLastFrame.of(firstFrame, firstFrame.getLastFrame());
    }

    public int size() {
        return firstAndLastFrame.getLast().index;
    }

    @Override
    public boolean isEnd() {
        return firstAndLastFrame.getLast().isEnd();
    }

    @Override
    public void setKnockDownPins(KnockDownPins knockDownPins) {
        currentFrame.setKnockDownPins(knockDownPins);
        if (currentFrame.isEnd()) {
            currentFrame = currentFrame.getNextFrame();
        }
    }

    @Override
    public int getCurrentFrameIndex() {
        return currentFrame.index;
    }

    @Override
    public Stream<FrameViewDto> viewDtoStream() {
        List<FrameViewDto> frameViewDtos = new ArrayList<>();

        Frame currentFrame = firstAndLastFrame.getFirst();
        while (currentFrame != null && currentFrame.index <= FramesViewDto.MAX_FRAME_SIZE) {
            frameViewDtos.add(currentFrame);
            currentFrame = currentFrame.getNextFrame();
        }

        return frameViewDtos.stream();
    }
}
