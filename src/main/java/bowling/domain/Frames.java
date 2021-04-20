package bowling.domain;

import bowling.service.BowlingGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 프레임의 비즈니스 로직을 관리하는 클래스
 * tryCount에 따라 frame 새로 추가
 */
public class Frames {

    private static final int FRAME_START_NUMBER = 0;
    private static final int FRAME_NEXT_NUMBER = 1;
    private final List<Frame> frames = new ArrayList<>();
    private int frameNumber;

    public Frames(Frame frame) {
        this.frames.add(frame);
        this.frameNumber = FRAME_START_NUMBER;
    }

    public void bowl(int pins) {
        if (isNextFrame()) {
            frames.add(Frame.init());
        }
        currentFrame().bowl(pins);
    }

    public void nextFrame() {
        frameNumber = frameNumber + FRAME_NEXT_NUMBER;
    }

    protected Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    public boolean isNextFrame() {
        return currentFrame().isNextFrame();
    }

    public int tryCount() {
        return currentFrame().tryCount();
    }

    public boolean isLastFrame() {
        return frameNumber == BowlingGame.BOWLING_LAST_FRAME;
    }

    public int frameNumber() {
        return frameNumber;
    }

    public List<Frame> list() {
        return Collections.unmodifiableList(frames);
    }

    @Override
    public String toString() {
        return String.valueOf(frames);
    }
}
