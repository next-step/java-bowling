package bowling.domain;

import bowling.domain.exception.OutOfRangeArgumentException;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final String OUT_OF_RANGE_ERROR_MESSAGE =
            "프레임 갯수는 최소 %d 이상이여야 합니다.";
    private static final int MIN = 2;
    private List<Frame> frames;
    private Frame currentFrame;

    public Frames(int size) {
        if (size < MIN) {
            throw new OutOfRangeArgumentException(
                    String.format(OUT_OF_RANGE_ERROR_MESSAGE, MIN));
        }
        frames = new ArrayList<>();
        initFrameList(size);
    }

    private void initFrameList(int size) {
        NormalFrame firstFrame = new NormalFrame();

        frames.add(firstFrame);
        currentFrame = firstFrame;

        NormalFrame frame = firstFrame;
        for (int i = 0, sizeWithoutLast = size - 2; i < sizeWithoutLast; i++) {
            frame = frame.createNext();
            frames.add(frame);
        }
        frames.add(frame.createFinal());
    }

    public Frames add(int pinCount) {
        if (!isAddable()) {
            return this;
        }

        currentFrame.addPinCount(pinCount);
        if (currentFrame.isDone() && !currentFrame.isLast()) {
            currentFrame = currentFrame.getNext();
        }

        return this;
    }

    public int getCurrentFrameIndex() {
        return frames.indexOf(currentFrame);
    }

    public boolean isLast() {
        return currentFrame.isLast();
    }

    public boolean isAddable() {
        return !currentFrame.isLast() || !currentFrame.isDone();
    }

    public List<PinCount> getFramePinCounts(int index) {
        return frames.get(index).getPinCounts();
    }

    public int size() {
        return frames.size();
    }
}
