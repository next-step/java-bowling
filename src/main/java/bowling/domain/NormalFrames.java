package bowling.domain;

import java.util.List;

public class NormalFrames {

    private static final int MIN_SIZE = 0;
    private static final int CORRECTION_VALUE = 1;

    private List<Frame> frames;

    private NormalFrames(List<Frame> frames) {
        this.frames = frames;
    }

    public static NormalFrames of(List<Frame> frames) {
        return new NormalFrames(frames);
    }

    public NormalFrames next(int countOfHit) {
        frames.add(createNormalFrame(countOfHit));
        return of(frames);
    }

    private Frame createNormalFrame(int countOfHit) {
        if (frames.size() > MIN_SIZE) {
            Frame frame = frames.get(frames.size() - CORRECTION_VALUE);
            if (frame.isRemain() && !frame.isStrike()) {
                return frame.nextFrame(countOfHit);
            }
        }
        return Frame.frame(countOfHit);
    }

    public long getCurrentFrameNumber() {
        return frames.stream()
                .filter(Frame::isCompleteNormalFrame)
                .count();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int size() {
        return frames.size();
    }
}
