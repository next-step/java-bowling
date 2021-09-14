package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frames {

    private final List<Frame> frames;

    public Frames() {
        frames = Stream.generate(NormalFrame::new)
                .limit(FrameNumber.LAST_FRAME_NUMBER)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public FrameNumber bowl(FrameNumber frameNumber, PinCount fallenPinCount) {
        Frame frame = frames.get(frameNumber.ordinal());
        frame.bowl(fallenPinCount);
        if(frame.isFinished()) {
            return frameNumber.next();
        }
        return frameNumber;
    }

    public List<Renderer> toFrameStateRenderers() {
        return frames.stream()
                .map(Frame::createFrameStateRenderer)
                .collect(Collectors.toList());
    }
}
