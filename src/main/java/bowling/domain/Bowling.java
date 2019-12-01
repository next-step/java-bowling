package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Bowling {

    private static final int MIN_SIZE = 0;
    private static final int CORRECTION_VALUE = 1;

    private List<Frame> frames;

    public Bowling() {
        this.frames = new ArrayList<>();
    }

    public void go(int countOfHit) {
        if (getCurrentFrame() < 10) {
            frames.add(createNormalFrame(countOfHit));
        } else {
            Frame frame = createFinalFrame(countOfHit);
            if (frame.isBonus(countOfHit)) {
                if (frame.isRemainLastFrame()) {
                    frames.add(createFinalFrame(countOfHit));
                } else {
                    frames.add(bonusFrame(countOfHit));
                }
            }
        }
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
        Frame frame = frames.get(frames.size() - CORRECTION_VALUE);
        if (frame.isRemainLastFrame()) {
            return frame.nextFinalFrame(countOfHit);
        }
        return Frame.finalFrame(countOfHit);
    }

    private Frame bonusFrame(int countOfHit) {
        Frame frame = frames.get(frames.size() - CORRECTION_VALUE);
        return frame.bonusFrame(countOfHit);
    }

    public List<Frame> getFrames() {
        return new ArrayList<>(frames);
    }

    public int getFrameSize() {
        return frames.size();
    }

    public long getCurrentFrame() {
        return frames.stream()
                .filter(Frame::isRemain)
                .count();
    }

    public boolean isContains(Frame frame) {
        return frames.contains(frame);
    }
}
