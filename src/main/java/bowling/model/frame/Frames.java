package bowling.model.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int GAP_BETWEEN_SIZE_AND_INDEX = 1;

    private final List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
    }

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public List<Frame> play(int fallenPinCount) {
        if (frames.isEmpty()) {
            return initialPlay(fallenPinCount);
        }
        return notInitialPlay(fallenPinCount);
    }

    private List<Frame> initialPlay(int fallenPinCount) {
        validateNextIsPlayable();

        Frame initialFrame = Frame.initial(fallenPinCount);
        frames.add(initialFrame);
        return frames;
    }

    private List<Frame> notInitialPlay(int fallenPinCount) {
        validateNextIsPlayable();

        Frame next = lastFrame().next(fallenPinCount);
        if (next.isFrameNumberEqual(lastFrame())) {
            int lastFrameIndex = frames.size() - GAP_BETWEEN_SIZE_AND_INDEX;
            frames.remove(lastFrameIndex);
        }

        frames.add(next);
        return frames;
    }

    private void validateNextIsPlayable() {
        if (!canPlayNext()) {
            throw new IllegalArgumentException("더 이상 게임을 진행할 수 없습니다.");
        }
    }

    public boolean canPlayNext() {
        if (frames.isEmpty()) {
            return true;
        }
        return lastFrame().canPlayNext();
    }

    private Frame lastFrame() {
        validateNotEmpty();

        int lastFrameIndex = frames.size() - GAP_BETWEEN_SIZE_AND_INDEX;
        return frames.get(lastFrameIndex);
    }

    private void validateNotEmpty() {
        if (frames.isEmpty()) {
            throw new IllegalArgumentException("프레임 컬렉션이 비었습니다.");
        }
    }

    public FrameNumber nextFrameNumber() {
        validateNextIsPlayable();

        if (frames.isEmpty()) {
            return FrameNumber.initial();
        }
        return lastFrame().nextNumber();
    }
}
