package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int COUNT_OF_FRAMES = 10;
    private static final int FRAME_INDEX_INITIAL = 0;
    private static final int FRAME_DIFFERENCE_INDEX_COUNT = 1;
    private static final int LAST_CALCULATED_FRAME_INDEX_INITIAL = 0;

    private final List<Frame> frames;
    private int frameIndex;
    private int lastCalculatedFrameIndex;

    private Frames() {
        frames = new ArrayList<>();
        for (int i = 1; i < COUNT_OF_FRAMES; i++) {
            frames.add(new NormalFrame());
        }
        frames.add(new FinalFrame());

        this.frameIndex = FRAME_INDEX_INITIAL;
        this.lastCalculatedFrameIndex = LAST_CALCULATED_FRAME_INDEX_INITIAL;
    }

    public static Frames init() {
        return new Frames();
    }

    public int size() {
        return frames.size();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public boolean canPlay() {
        if (frameIndex == FRAME_INDEX_INITIAL) {
            return true;
        }

        if (frameIndex >= COUNT_OF_FRAMES) {
            return false;
        }

        return frames.get(frameIndex).isRollable();
    }

    public void play(int knockedDownPinCount) {
        Frame currentFrame = frames.get(frameIndex);
        currentFrame.rollingBall(knockedDownPinCount);
        calculateScore(knockedDownPinCount);

        if (!currentFrame.isRollable()) {
            ++frameIndex;
        }
    }

    public int getFrameNumber() {
        return frameIndex + FRAME_DIFFERENCE_INDEX_COUNT;
    }

    private void calculateScore(int knockedDownPinCount) {
        Frame lastCalculatedFrame = frames.get(lastCalculatedFrameIndex);
        if (lastCalculatedFrame.isRollable()) {
            return;
        }

        calculateScore(knockedDownPinCount, lastCalculatedFrame);
    }

    private void calculateScore(int knockedDownPinCount, Frame lastCalculatedFrame) {
        if (!isAnyFrameNeedCalculateScore()) {
            calculateScoreOfFrame(lastCalculatedFrame);
            return;
        }

        calculateRemainFrames(knockedDownPinCount, lastCalculatedFrame);
    }

    private boolean isAnyFrameNeedCalculateScore() {
        return frameIndex != LAST_CALCULATED_FRAME_INDEX_INITIAL
                && lastCalculatedFrameIndex != frameIndex;
    }

    private void calculateScoreOfFrame(Frame frame) {
        frame.calculateScore(getLastCalculatedFrame());
        checkLastCalculateFrameIndex(frame);
    }

    private Frame getLastCalculatedFrame() {
        if (lastCalculatedFrameIndex == LAST_CALCULATED_FRAME_INDEX_INITIAL) {
            return null;
        }

        return frames.get(lastCalculatedFrameIndex - FRAME_DIFFERENCE_INDEX_COUNT);
    }

    private void checkLastCalculateFrameIndex(Frame frame) {
        if (frame.isScoreCalculateDone()) {
            ++lastCalculatedFrameIndex;
        }
    }

    private void calculateRemainFrames(int knockedDownPinCount, Frame lastCalculatedFrame) {
        calculateAdditionalScore(knockedDownPinCount, lastCalculatedFrame);

        while (lastCalculatedFrameIndex <= frameIndex && lastCalculatedFrame.isScoreCalculateDone()) {
            lastCalculatedFrame = frames.get(lastCalculatedFrameIndex);
            calculateFrameBeforeCurrent(lastCalculatedFrameIndex, lastCalculatedFrame);
        }
    }

    private void calculateFrameBeforeCurrent(int index, Frame lastCalculatedFrame) {
        if (lastCalculatedFrame.isRollable()) {
            return;
        }

        calculateScoreOfFrame(lastCalculatedFrame);
        calculateAdditionalScoreByNextFrame(index + FRAME_DIFFERENCE_INDEX_COUNT, lastCalculatedFrame);
    }

    private void calculateAdditionalScore(int knockedDownPinCount, Frame frame) {
        frame.addScore(knockedDownPinCount);
        checkLastCalculateFrameIndex(frame);
    }

    private void calculateAdditionalScoreByNextFrame(int nextFrameIndex, Frame lastFrame) {
        for (int j = nextFrameIndex; j <= frameIndex && !lastFrame.isScoreCalculateDone(); j++) {
            Frame nextFrame = frames.get(j);

            nextFrame.addAdditionalScoreOfLastFrame(lastFrame);
        }
    }
}
