package bowling.domain.frame;

import bowling.domain.score.Score;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Frames {

    private static final String STRIKE_EXPRESSION = "X";
    private static final String SPARE_EXPRESSION = "/";
    private static final String EMPTY_STRING = "";
    private static final int ZERO_POINT = 0;

    private static final int MIN_FRAME_NUMBER = 0;
    private static final int MAX_FRAME_NUMBER = 10;

    private final List<Frame> frames = new LinkedList<>();
    private final List<Integer> points = new LinkedList<>();
    private int frameIndex = 0;

    private Frames()  {
        validateFrameIndex(frameIndex);
        frames.add(NormalFrame.create());
        points.add(0);
    }

    public static Frames create() {
        return new Frames();
    }

    public Frames pitch(Point point) {
        Frame frame = frames.get(frameIndex).bowl(point);
        updateFrame(frame);
        updatePoint(frame.getPoint());
        return this;
    }

    private void updatePoint(int framePoint) {
        points.remove(frameIndex);
        points.add(framePoint);
    }

    private void updateFrame(Frame frame) {
        frames.remove(frameIndex);
        frames.add(frame);
    }

    public void next() {
        if(frames.get(frameIndex).isLastPitch()) {
            frames.add(createNextFrame());
            points.add(0);
            frameIndex++;
        }
    }

    private Frame createNextFrame() {
        if (isBeforeLastIndex()) {
            return FinalFrame.create();
        }
        return NormalFrame.create();
    }

    public List<Integer> getPoints() {
        return points;
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public boolean isLastFrame() {
        return frameIndex == MAX_FRAME_NUMBER;
    }

    public int getFrameIndex() {
        return frameIndex;
    }

    public int size() {
        return frames.size();
    }

    private boolean isBeforeLastIndex() {
        return frameIndex == 8;
    }

    private void validateFrameIndex(int frameIndex) {
        if (frameIndex < MIN_FRAME_NUMBER) {
            throw new IllegalArgumentException("프레임은 0보다 작을 수 없습니다.");
        }

        if (frameIndex > MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException("프레임은 10보다 클 수 없습니다.");
        }

        if (frameIndex > frames.size()) {
            throw new IllegalArgumentException("index는 frames의 크기보다 클 수 없습니다.");
        }
    }
}

