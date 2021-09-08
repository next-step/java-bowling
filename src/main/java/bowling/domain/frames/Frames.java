package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.exception.FinishGameException;
import bowling.domain.exception.FrameSizeExceededException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private static final int NUMBER_OF_MAX_FRAME = 10;

    private List<Frame> frames;
    private boolean isFinish;
    // TODO 언제 finish?

    public Frames() {
        this.frames = new ArrayList<>();
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

    public void roll(final Score score) {
        // TODO indent
        for (Frame frame : frames) {
            if (!frame.isFinish()) {
                frame.roll(score);
                return;
            }
        }
        throw new FinishGameException();
    }

    public boolean isFinish() {
        return this.isFinish;
    }

    private void checkFrameSize(final List<Frame> frames) {
        if (frames.size() != NUMBER_OF_MAX_FRAME) {
            throw new FrameSizeExceededException();
        }
    }

    public List<Frame> elements() {
        return Collections.unmodifiableList(this.frames);
    }

    @Override
    public String toString() {
        return "Frames{" +
                "frames=" + frames +
                ", isFinish=" + isFinish +
                '}';
    }
}
