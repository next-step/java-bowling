package bowling.domain.frame;

import bowling.domain.dto.FrameResult;
import bowling.domain.pin.PinCount;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

    private final List<Frame> frames;

    private Frames() {
        this.frames = new ArrayList<>();
        frames.add(NormalFrame.ofFirst());
    }

    public static Frames of() {
        return new Frames();
    }

    public void bowl(final PinCount hitCount) {
        Frame frame = getCurrentFrame().bowl(hitCount);
        frame.addFrame(this);
    }

    public void add(final Frame frame) {
        frames.add(frame);
    }

    public boolean isGameOver() {
        return getCurrentFrame().isGameOver();
    }

    public boolean isSameCurrentFrame(final Frame frame) {
        return this.getFrameNumber() == frame.getNo();
    }

    public int getFrameNumber() {
        return getCurrentFrame().getNo();
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }

    public List<FrameResult> getFrameResult() {
        return frames.stream()
                .map(Frame::getFrameResult)
                .collect(Collectors.toList());
    }
}
