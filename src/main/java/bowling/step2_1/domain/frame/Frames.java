package bowling.step2_1.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Frames {
    private static final int INIT_NUMBER = 1;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int LAST_FRAME_NO = 10;
    private static final int LAST_FRAME_INDEX = 9;
    private static final String FRAME_FORMAT = "%-4s";
    private static final String FRAME_DELIMITER = "|  ";

    private final List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
    }

    public boolean isEnd() {
        return frames.size() == LAST_FRAME_NO && frames.get(LAST_FRAME_INDEX).isFrameEnd();
    }

    public int getFramesSize() {
        return frames.size();
    }

    public Frame getLastFrame() {
        if(isFirst()){
            frames.add(new NormalFrame(INIT_NUMBER));
        }
        return frames.get(lastIndex());
    }

    private boolean isFirst() {
        return !Objects.isNull(frames) && frames.size() == ZERO;
    }

    private int lastIndex() {
        return frames.size() - ONE;
    }

    public void progress(int pitch) {
        Frame nowFrame = getLastFrame().pitch(pitch);
        if (nowFrame.isFrameEnd()){
            nextFrame(nowFrame);
        }
    }

    private void nextFrame(Frame nowFrame) {
        Frame nextFrame = nowFrame.next();
        if (!Objects.isNull(nextFrame)){
            frames.add(nextFrame);
        }
    }

    @Override
    public String toString() {
        return frames.stream()
                .map(frame -> String.format(FRAME_FORMAT, frame.toString()))
                .collect(Collectors.joining(FRAME_DELIMITER));
    }
}
