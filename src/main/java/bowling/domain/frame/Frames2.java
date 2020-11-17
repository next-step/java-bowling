package bowling.domain.frame;

import bowling.domain.point.Point;

import java.util.LinkedList;
import java.util.List;

public class Frames2 {
    private static final int MIN_FRAME_INDEX = 0;
    private static final int MAX_FRAME_INDEX = 10;
    private static final String FRAME_MIN_ERROR = "프레임은 0보다 커야합니다";
    private static final String FRAME_MAX_ERROR = "프레임은 10보다 클 수 없습니다";
    private static final String STRIKE_SIGN = "X";
    private static final String SPARE_SIGN = "/";
    private static final String NONE = "";
    private static final int BASE_INDEX = 0;
    private static final int ZERO = 0;
    private static final int LAST_NORMAL_FRAME_INDEX = 8;

    private final List<Frame2> frames = new LinkedList<>();
    private final List<String> points = new LinkedList<>();
    private int frameIndex;

    private Frames2() {
        validate(frameIndex);
        this.frameIndex = BASE_INDEX;
        frames.add(NormalFrame2.of());
        points.add(convertPoint());
    }

    public static Frames2 generate() {
        return new Frames2();
    }

    public void nextFrame() {
        if (frames.get(frameIndex).isLastRoll()) {
            frames.add(generateNextFrame());
            points.add(NONE);
            frameIndex++;
        }
    }

    public Frames2 roll(Point point) {
        Frame2 frame = frames.get(frameIndex).roll(point);
        updateFrame(frame);
        return this;
    }

    private void updateFrame(Frame2 frame) {
        frames.remove(frameIndex);
        frames.add(frame);
    }

    private Frame2 generateNextFrame() {
        if (isNormalFrameIndexLimit()) {
            return FinalFrame2.of();
        }
        return NormalFrame2.of();
    }

    private String convertPoint() {
        Frame2 currentFrame = frames.get(frameIndex);
        if (currentFrame.getScores().equals(STRIKE_SIGN) && currentFrame.isLastRoll()) {
            return NONE;
        }
        if (currentFrame.getScores().equals(SPARE_SIGN) && currentFrame.isLastRoll()) {
            return NONE;
        }
        if (currentFrame.getScores().equals(ZERO) && currentFrame.isLastRoll()) {
            return String.valueOf(currentFrame.getPoint());
        }
        if (currentFrame.isLastRoll()) {
            return String.valueOf(currentFrame.getPoint());
        }
        return NONE;
    }

    public boolean isGameOver() {
        return frameIndex == MAX_FRAME_INDEX;
    }

    private boolean isNormalFrameIndexLimit() {
        return frameIndex == LAST_NORMAL_FRAME_INDEX;
    }

    private void validate(int frameIndex) {
        if (frameIndex < MIN_FRAME_INDEX) {
            throw new IllegalArgumentException(FRAME_MIN_ERROR);
        }
        if (frameIndex > MAX_FRAME_INDEX) {
            throw new IllegalArgumentException(FRAME_MAX_ERROR);
        }
    }

    public LinkedList<Frame2> getFrames() {
        return new LinkedList<>(frames);
    }

    public int getFrameIndex() {
        return frameIndex;
    }

    public int getSize() {
        return frames.size();
    }
}
