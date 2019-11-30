package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Bowling {

    private List<Frame> frames;

    public Bowling() {
        this.frames = new ArrayList<>();
    }

    public void go(int countOfHit) {
        frames.add(create(countOfHit));
    }

    private Frame create(int countOfHit) {
        if (frames.size() > 0) {
            Frame frame = frames.get(frames.size() - 1);
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
