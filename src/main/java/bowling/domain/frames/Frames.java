package bowling.domain.frames;

import bowling.domain.exception.FrameSizeExceededException;

import java.util.List;

public class Frames {

    private static final int NUMBER_OF_MAX_FRAME = 10;

    private List<Frame> frames;

    public Frames() {
        init();
    }

    public Frames(final List<Frame> frames) {
        checkFrameSize(frames);
        this.frames = frames;
    }

    private void init() {
        for (int i = 0; i < 9; i++) {
            frames.add(new NormalFrame());
        }
        frames.add(new FinalFrame());
    }

    private void checkFrameSize(List<Frame> frames) {
        if (frames.size() != NUMBER_OF_MAX_FRAME) {
            throw new FrameSizeExceededException();
        }
    }
}
