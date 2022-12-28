package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    public static final int MAX_FRAME_NUMBER = 10;

    private final List<Frame> frames;

    public Frames() {
        frames = new ArrayList<>();
        frames.add(new NormalFrame(1));
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(frames);
    }

    public int currentFrameNumber() {
        return currentFrame().frameNumber();
    }

    public void bowl(Pin pin) {
        currentFrame().bowl(pin);
        nextFrame();
    }

    private void nextFrame() {
        if (frames.size() == MAX_FRAME_NUMBER) {
            return;
        }
        if (currentFrame().isFinished()) {
            frames.add(currentFrame().nextFrame());
        }
    }

    // todo 아래 메소드 어떻게 테스트할지 고민
    public boolean gameFinished() {
        if (frames.size() < MAX_FRAME_NUMBER) {
            return false;
        }
        return currentFrame().isFinished();
    }

    private Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

}
