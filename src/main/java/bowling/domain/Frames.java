package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int SIZE = 10;
    private List<Frame> frameList;
    private Frame currentFrame;

    public Frames() {
        frameList = new ArrayList<>();
        initFrameList();

    }

    private void initFrameList() {
        NormalFrame firstFrame = new NormalFrame();

        frameList.add(firstFrame);
        currentFrame = firstFrame;

        NormalFrame frame = firstFrame;
        for (int i = 0, sizeWithoutLast = SIZE - 2; i < sizeWithoutLast; i++) {
            frame = frame.createNext();
            frameList.add(frame);
        }
        frameList.add(frame.createFinal());
    }

    public void add(int pinCount) {
        currentFrame.addPinCount(pinCount);
        if (currentFrame.isDone()) {
            currentFrame = currentFrame.getNext();
        }
    }

    public int getCurrentFrameIndex() {
        return frameList.indexOf(currentFrame);
    }

    public boolean isLast() {
        return currentFrame.isLast();
    }
}
