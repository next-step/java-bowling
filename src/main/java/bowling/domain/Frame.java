package bowling.domain;

import bowling.InvalidFrameIndexException;

import static util.Preconditions.checkArgument;

public abstract class Frame {
    private static final int FINAL_FRAME_INDEX = 9;

    private final int index;
    private Frame nextFrame;

    protected Frame(final int index) {
        this.index = index;
    }

    public static Frame first() {
        return new NormalFrame(0);
    }

    public Frame next() {
        checkArgument(index < FINAL_FRAME_INDEX, new InvalidFrameIndexException());

        if (isBeforeFinal()) {
            nextFrame = new FinalFrame(index + 1);
            return nextFrame;
        }

        nextFrame = new NormalFrame(index + 1);
        return nextFrame;
    }

    private boolean isBeforeFinal() {
        return index == FINAL_FRAME_INDEX - 1;
    }
}