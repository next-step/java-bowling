package bowling.domain;

import bowling.domain.exception.OutOfRangeArgumentException;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final String OUT_OF_RANGE_ERROR_MESSAGE =
            "프레임 갯수는 최소 %d 이상이여야 합니다.";
    private static final int MIN = 2;
    private List<Frame> frameList;
    private Frame currentFrame;

    public Frames(int size) {
        if (size < MIN) {
            throw new OutOfRangeArgumentException(
                    String.format(OUT_OF_RANGE_ERROR_MESSAGE, MIN));
        }
        frameList = new ArrayList<>();
        initFrameList(size);
    }

    private void initFrameList(int size) {
        NormalFrame firstFrame = new NormalFrame();

        frameList.add(firstFrame);
        currentFrame = firstFrame;

        NormalFrame frame = firstFrame;
        for (int i = 0, sizeWithoutLast = size - 2; i < sizeWithoutLast; i++) {
            frame = frame.createNext();
            frameList.add(frame);
        }
        frameList.add(frame.createFinal());
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
        return frameList.indexOf(currentFrame);
    }

    public boolean isLast() {
        return currentFrame.isLast();
    }

    public boolean isAddable() {
        return !currentFrame.isLast() || !currentFrame.isDone();
    }

    public List<PinCount> getFramePinCounts(int index) {
        return frameList.get(index).getPinCounts();
    }

    public int size() {
        return frameList.size();
    }
}
