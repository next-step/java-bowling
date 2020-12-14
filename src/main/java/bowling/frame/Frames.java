package bowling.frame;

import java.util.LinkedList;

import static bowling.frame.Frame.FINAL_FRAME_NUMBER;
import static bowling.frame.Frame.INCREASE_FRAME_NUMBER;
import static bowling.global.utils.ExceptionMessage.MESSAGE_GAME_OVER;

public class Frames {

    private static final int DECREASE_FRAME_NUMBER = 1;

    private final LinkedList<Frame> frames;

    private Frames(LinkedList<Frame> frames) {
        this.frames = frames;
        this.frames.add(NormalFrame.first());
    }

    public static Frames from(LinkedList<Frame> frames) {
        return new Frames(frames);
    }

    public Frame bowl(String fellPins) {
        Frame frame = getLastFrame().bowl(fellPins);

        if (canMoveNextFrame() && isLastFrameFinish()) {
            addNextFrame(getNextFrameNumber());
        }
        return frame;
    }

    public void addNextFrame(int nextFrameNumber) {
        Frame nextFrame = next(nextFrameNumber);
        frames.add(nextFrame);
    }

    public Frame next(int nextFrameNumber) {
        validateNextFrame();
        if (nextFrameNumber == FINAL_FRAME_NUMBER) {
            return FinalFrame.create();
        }
        return NormalFrame.create(nextFrameNumber);
    }

    private void validateNextFrame() {
        if (!canMoveNextFrame()) {
            throw new RuntimeException(MESSAGE_GAME_OVER);
        }
    }

    public boolean canMoveNextFrame() {
        return getNextFrameNumber() <= FINAL_FRAME_NUMBER;
    }

    public boolean isEnd() {
        return !canMoveNextFrame() && isLastFrameFinish();
    }

    public boolean isCurrentFrameFinish(int frameNumber) {
        return frames.get(frameNumber - DECREASE_FRAME_NUMBER)
                .isFinish();
    }

    private boolean isLastFrameFinish() {
        return getLastFrame().isFinish();
    }

    public int getFrameNumber() {
        return getLastFrame().getFrameNumber();
    }

    public int getNextFrameNumber() {
        return getFrameNumber() + INCREASE_FRAME_NUMBER;
    }

    public Frame getLastFrame() {
        return frames.getLast();
    }

    public LinkedList<Frame> getFrames() {
        return frames;
    }

    public int size() {
        return frames.size();
    }

}
