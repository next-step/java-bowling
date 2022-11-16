package bowling.domain.frame;

import static bowling.domain.frame.Frame.*;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final String INVALID_FRAME_NUMBER_EXCEPTION_MESSAGE = "허용되지 않는 프레임 숫자 입니다.";

    private final List<Frame> frames;

    public static Frame createNextFrame(int frameNumber) {
        if (frameNumber >= LAST_FRAME) {
            throw new IllegalArgumentException(INVALID_FRAME_NUMBER_EXCEPTION_MESSAGE);
        }

        if (frameNumber + 1 == LAST_FRAME) {
            return new LastFrame();
        }

        return new NormalFrame(frameNumber + 1);
    }

    public static Frames init() {
        return new Frames(new ArrayList<>(List.of(new NormalFrame(START_FRAME))));
    }

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public void bowl(int falledPins) {
        getCurrentFrame().bowl(falledPins);
    }

    public void createNextFrame() {
        frames.add(getCurrentFrame().createNextFrame());
    }

    public boolean isCurrentFrameEnded() {
        return getCurrentFrame().isFrameFinish();
    }

    public int getCurrentFrameNumber() {
        return getCurrentFrame().getFrameNumber();
    }

    public boolean isGamePlayable() {
        return !(frames.size() == LAST_FRAME && isCurrentFrameEnded());
    }

    public List<Frame> getValue() {
        return frames;
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }
}
