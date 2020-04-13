package bowling.domain;

import bowling.domain.exception.OutOfRangeArgumentException;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final String OUT_OF_RANGE_ERROR_MESSAGE =
            "프레임 갯수는 최소 %d 이상이여야 합니다.";
    private static final int MIN = 2;
    private static final int FIRST_INDEX = 0;

    private List<Frame> frames;

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
        NormalFrame frame = firstFrame;
        for (int i = 0, sizeWithoutLast = size - 2; i < sizeWithoutLast; i++) {
            frame = frame.createNext();
            frames.add(frame);
        }
        frames.add(frame.createFinal());
    }

    public int getCurrentFrameIndex(Frame frame) {
        return frames.indexOf(frame);
    }

    public List<PinCount> getFramePinCounts(int index) {
        return frames.get(index).getPinCounts();
    }

    public int size() {
        return frames.size();
    }

    public Frame getFirstFrame() {
        return frames.get(FIRST_INDEX);
    }
}
