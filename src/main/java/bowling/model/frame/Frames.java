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

    public void play(int fallenPinCount) {
        if (frames.isEmpty()) {
            initialPlay(fallenPinCount);
            return;
        }
        nextPlay(fallenPinCount);
    }

    private void initialPlay(int fallenPinCount) {
        validateNextIsPlayable();

        Frame initialFrame = Frame.initial(fallenPinCount);
        frames.add(initialFrame);
    }

    private void nextPlay(int fallenPinCount) {
        validateNextIsPlayable();

        Frame nextFrame = lastFrame().next(fallenPinCount);
        if (nextFrame.isFrameNumberEqual(lastFrame())) {
            int lastFrameIndex = frames.size() - GAP_BETWEEN_SIZE_AND_INDEX;
            frames.remove(lastFrameIndex);
        }
        calculateStrikeScoreSpare(nextFrame);

        frames.add(nextFrame);
    }

    private void calculateStrikeScoreSpare(Frame nextFrame) {
        if (frames.isEmpty()) {
            return;
        }

        boolean isStrikeCanBeCalculated = lastFrame().isStrike() && nextFrame.pitchTwice();
        boolean isSpareCanBeCalculated = lastFrame().isSpare() && !nextFrame.pitchTwice();

        if (isStrikeCanBeCalculated || isSpareCanBeCalculated) {
            lastFrame().calculateStrikeOrSpareScore(nextFrame);
        }
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

    public int scoreValue(int index) {
        return frames.get(index).scoreValue();
    }

    public List<Frame> frames() {
        return frames;
    }
}
