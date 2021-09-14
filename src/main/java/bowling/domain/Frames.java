package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frames {

    private static final int NORMAL_FRAME_COUNT = 9;

    private final List<Frame> frames;

    public Frames() {
        frames = Stream.generate(NormalFrame::new)
                .limit(NORMAL_FRAME_COUNT)
                .collect(Collectors.toList());
        frames.add(new FinalFrame());
    }

    public void bowl(FrameNumber frameNumber, PinCount fallenPinCount) {
        Frame frame = frames.get(frameNumber.frameIndex());
        frame.bowl(fallenPinCount);
        if(frame.isFinished()) {
            frameNumber.increase();
        }
    }

    public List<Renderer> toRenderers() {
        return frames.stream()
                .map(Frame::toRenderer)
                .collect(Collectors.toList());
    }
}
