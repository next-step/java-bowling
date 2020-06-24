package bowling.domain.frame;

import bowling.domain.dto.StateDtos;
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

    public static Frames newInstance() {
        return new Frames();
    }

    public void bowl(final PinCount hitCount) {
        Frame frame = getCurrentFrame();

        frame.bowl(hitCount);
        frame.addFrame(this);
    }

    public void add(final Frame frame) {
        frames.add(frame);
    }

    public boolean isGameOver() {
        return getCurrentFrame().isGameOver();
    }

    public int getFrameNumber() {
        return getCurrentFrame().getFrameNo();
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }

    public List<StateDtos> getFrameResult() {
        return frames.stream()
                .map(Frame::getFrameResult)
                .collect(Collectors.toList());
    }
}
