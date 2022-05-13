package bowling.model.frame;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrameFactory {

    private static final int START_FRAME_NUMBER = 1;

    private static final int LAST_FRAME_NUMBER = 10;

    public List<Frame> create() {
        List<Frame> frames = createNormalFrames();
        frames.add(createLastFrame());
        return frames;
    }

    private List<Frame> createNormalFrames() {
        return IntStream.range(START_FRAME_NUMBER, LAST_FRAME_NUMBER)
                .mapToObj(frameNumber -> NormalFrame.create())
                .collect(Collectors.toList());
    }

    private Frame createLastFrame() {
        return LastFrame.create();
    }

}
