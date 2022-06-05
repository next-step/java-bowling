package bowling.domain.frame;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrameFactory {
    private static final int START_FRAME_NUMBER = 1;
    private static final int LAST_FRAME_NUMBER = 10;

    private FrameFactory() {
    }

    public static List<Frame> create() {
        List<Frame> frames = createNormalFrames();
        frames.add(createFinalFrame());
        return frames;
    }

    private static List<Frame> createNormalFrames() {
        return IntStream.range(START_FRAME_NUMBER, LAST_FRAME_NUMBER)
                .mapToObj(frameNumber -> NormalFrame.initialize()).collect(Collectors.toList());
    }

    private static Frame createFinalFrame() {
        return FinalFrame.initialize();
    }
}
