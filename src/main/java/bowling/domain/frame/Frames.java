package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 프레임 일급 컬렉션
 * 프레임의 추가, 프레임의 전체 점수를 계산하는 책임을 가진다.
 */
public class Frames {
    private static final int BOWLING_FRAME_SIZE = 10;
    private static final int FRAME_INDEX_NINE = 9;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final String NOT_ADDABLE_FRAME = "";

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames of() {
        List<Frame> frames = new ArrayList<>();
        return new Frames(frames);
    }

    public void add(Frame frame) {
        frames.add(frame);
    }

    public void addNextFrame() {
        if (isFirst()) {
            frames.add(DefaultFrame.first());
            return;
        }

        DefaultFrame currentFrame = (DefaultFrame) getLast();
        int currentIndex = frames.size() - ONE;
        if (isLast()) {
            frames.add(currentFrame.createLastFrame(currentIndex));
            return;
        }
        frames.add(currentFrame.createNextFrame(currentIndex));
    }

    private boolean isLast() {
        return !Objects.isNull(frames) && frames.size() == FRAME_INDEX_NINE;
    }

    private boolean isFirst() {
        return !Objects.isNull(frames) && frames.size() == ZERO;
    }

    public String calculateFramePoint(int frameIndex) {
        if (!isCalculatableFrame(frameIndex)) {
            return NOT_ADDABLE_FRAME;
        }

        int totalPoint = ZERO;
        for (int i = 0; i < frameIndex + 1; i++) {
            totalPoint += frames.get(i).getTotalPoint(i);
        }
        return Integer.toString(totalPoint);
    }

    private boolean isCalculatableFrame(int frameIndex) {
        return !Objects.isNull(frames) && frames.get(frameIndex).isCalculatableFrame(frameIndex);
    }

    public Frame getLast() {
        return frames.get(lastIndex());
    }

    private int lastIndex() {
        return frames.size() - ONE;
    }

    public boolean isOver() {
        return frames.size() == BOWLING_FRAME_SIZE;
    }

    public int size() {
        return frames.size();
    }

    public Frame get(int index) {
        return frames.get(index);
    }
}
