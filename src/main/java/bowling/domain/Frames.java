package bowling.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 프레임의 비즈니스 로직을 관리하는 클래스
 * tryCount에 따라 frame 새로 추가
 */
public class Frames {

    private final List<Frame> frames = new ArrayList<>();

    public Frames(Frame frame) {
        this.frames.add(frame);
    }

    public void bowl(int pins) {
        if (isNextFrame()) {
            frames.add(nextFrame());
        }
        currentFrame().bowl(pins);
    }

    private Frame nextFrame() {
        return new Frame(0, 0);
    }

    public boolean isNextFrame() {
        return currentFrame().isNextFrame();
    }

    public int tryCount() {
        return currentFrame().tryCount();
    }

    protected Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    @Override
    public String toString() {
        return String.valueOf(frames);
    }
}
