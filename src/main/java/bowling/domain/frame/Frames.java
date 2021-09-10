package bowling.domain.frame;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private static final int MAX_FRAMES_SIZE = 10;
    private static final int START_FRAME_NUMBER = 1;
    private static final int NEXT_NUMBER = 1;

    private LinkedList<Frame> frames;

    public Frames(final LinkedList<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        return new Frames(new LinkedList<>(Arrays.asList(NormalFrame.of(START_FRAME_NUMBER))));
    }

    public boolean isFramesEnd() {
        return frames.size() == MAX_FRAMES_SIZE && frames.get(currentFrameIndex()).isEnd();
    }

    public int currentFrameIndex() {
        return frames.size() - 1;
    }

    public Frame pitch(final int countOfPins) {
        Frame currentFrame = frames.get(currentFrameIndex());
        if (!currentFrame.isEnd()) {
            currentFrame.pitch(countOfPins);
            return frames.get(currentFrameIndex());
        }
        if (isFinal(frames.size() + NEXT_NUMBER)) {
            frames.add(new FinalFrame(Arrays.asList(countOfPins)));
            return frames.get(currentFrameIndex());
        }

        frames.add(new NormalFrame(frames.size() + NEXT_NUMBER, Arrays.asList(countOfPins)));
        return frames.get(currentFrameIndex());
    }

    public boolean isFinal(int frameNumber) {
        return frameNumber == MAX_FRAMES_SIZE;
    }

    public int valueOfCurrentFrameNumber() {
        if (frames.get(currentFrameIndex()).isEnd()) {
            return frames.size() + NEXT_NUMBER;
        }
        return frames.size();
    }

    public List<String> resultsByCurrent() {
        return frames.stream()
                .map(Frame::valueOfFrame)
                .collect(Collectors.toList());
    }

}