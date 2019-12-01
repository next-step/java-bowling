package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Bowling {

    private static final int MIN_SIZE = 0;
    private static final int REMAIN_ZERO = 0;
    private static final int FINAL_FRAME_SIZE = 2;
    private static final int CORRECTION_VALUE = 1;

    private List<Frame> frames;
    private List<Frame> finalFrames;

    public Bowling() {
        this.frames = new ArrayList<>();
        this.finalFrames = new ArrayList<>();
    }

    public void go(int countOfHit) {
        if (getCurrentFrame() <= 8) {
            frames.add(createNormalFrame(countOfHit));
        } else {
            finalFrames.add(createFinalFrame(countOfHit));
        }
    }

    public boolean isBonus() {
        int sum = 0;
        for (Frame finalFrame : finalFrames) {
            if (finalFrame.isBonus(sum)) {
                return true;
            }
            sum += finalFrame.getCountOfHit();
        }
        return false;
    }

    private Frame createNormalFrame(int countOfHit) {
        if (frames.size() > MIN_SIZE) {
            Frame frame = frames.get(frames.size() - CORRECTION_VALUE);
            if (frame.isSecond()) {
                return frame.nextFrame(countOfHit);
            }
        }
        return Frame.normalFrame(countOfHit);
    }

    private Frame createFinalFrame(int countOfHit) {
        if (finalFrames.size() == FINAL_FRAME_SIZE) {
            if (isBonus()) {
                return bonusFrame(countOfHit);
            } else {
                throw new IllegalArgumentException("game over");
            }
        }
        if (finalFrames.size() > MIN_SIZE) {
            Frame frame = finalFrames.get(finalFrames.size() - CORRECTION_VALUE);
            if (frame.isRemain()) {
                return frame.nextFinalFrame(countOfHit);
            }
        }
        return Frame.finalFrame(countOfHit);
    }

    private Frame bonusFrame(int countOfHit) {
        Frame frame = finalFrames.get(finalFrames.size() - CORRECTION_VALUE);
        return frame.bonusFrame(countOfHit);
    }

    public List<Frame> getFrames() {
        return new ArrayList<>(frames);
    }

    public List<Frame> getFinalFrames() {
        return new ArrayList<>(finalFrames);
    }

    public long getCurrentFrame() {
        return frames.stream()
                .filter(frame -> frame.isStrike() || !frame.isRemain())
                .count();
    }

    public boolean isContains(Frame frame) {
        return frames.contains(frame);
    }
}
