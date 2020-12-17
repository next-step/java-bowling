package bowling.domain.frames;

import bowling.domain.KnockDownPins;
import bowling.dto.FrameDto;
import bowling.dto.FramesDto;

import java.util.ArrayList;
import java.util.List;

public class Frames {
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
        return firstAndLastFrame.getLastFrameIndex();
    }

    public boolean isEnd() {
        return firstAndLastFrame.isEnd();
    }

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        currentFrame.setKnockDownPins(knockDownPins);
        if (currentFrame.isEnd()) {
            currentFrame = currentFrame.getNextFrame();
        }
    }

    public int getCurrentFrameIndex() {
        return currentFrame.index;
    }

    public FramesDto convertToDto() {
        List<FrameDto> frames = new ArrayList<>();

        Frame currentFrame = firstAndLastFrame.getFirst();
        while (existFrame(currentFrame)) {
            frames.add(currentFrame.convertToFrameDto());
            currentFrame = currentFrame.getNextFrame();
        }

        return FramesDto.of(frames);
    }

    private boolean existFrame(Frame currentFrame) {
        return currentFrame != null;
    }
}
