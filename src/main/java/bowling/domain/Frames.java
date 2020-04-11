package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private List<Frame> frameList;
    private Frame currentFrame;

    public Frames(int size) {
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
}
