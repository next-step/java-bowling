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
        frames.add(create(countOfHit));
    }

    private Frame create(int countOfHit) {
        if (frames.size() > MIN_SIZE) {
            Frame frame = frames.get(frames.size() - CORRECTION_VALUE);
            if (frame.isSecond()) {
                return frame.nextFrame(countOfHit);
            }
        }
        return Frame.firstFrame(countOfHit);
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
