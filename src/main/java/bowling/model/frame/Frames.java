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

        decreaseRemainingPitchingCountOne();
        calculateRemainingScore(nextFrame);

        frames.add(nextFrame);
    }

    private void calculateRemainingScore(Frame nextFrame) {
        if (frames.isEmpty()) {
            return;
        }

        Frame lastFrame = lastFrame();
        boolean isStrikeCanBeCalculated = lastFrame.isStrike() && !lastFrame.remainsNextPitching();
        boolean isSpareCanBeCalculated = lastFrame.isSpare() && !lastFrame.remainsNextPitching() && !nextFrame.pitchTwice();

        if (isStrikeCanBeCalculated || isSpareCanBeCalculated) {
            lastFrame.calculateRemainingScore(nextFrame);
        }
    }

    private void decreaseRemainingPitchingCountOne() {
        frames.stream()
                .filter(Frame::remainsNextPitching)
                .forEach(Frame::decreaseRemainingPitchingCountOne);
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