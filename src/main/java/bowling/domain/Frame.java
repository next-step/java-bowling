package bowling.domain;

import bowling.InvalidFrameIndexException;

import java.util.Optional;

import static util.Preconditions.checkArgument;

public abstract class Frame {
    public static final int FINAL_FRAME_INDEX = 9;
    protected Pins firstPitching;
    protected Pins secondPitching;
    private final int index;
    private Frame nextFrame;

    protected Frame(final int index) {
        this.index = index;
    }

    public static Frame createFirst() {
        return new NormalFrame(0);
    }

    public Frame createNext() {
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

    public abstract void pitch(Pins pins);

    public boolean isPitchdisable() {
        return !isPitchable();
    }

    public abstract boolean isPitchable();

    public enum State {
        BEFORE_FIRST_PITCHING,
        BEFORE_SECOND_PITCHING,
        STRIKE,
        SPARE,
        MISS,
        ;

        public boolean isPitchable() {
            return this == BEFORE_FIRST_PITCHING || this == BEFORE_SECOND_PITCHING;
        }

        public static State nextState(final Pins firstPitching, final Pins secondPitching) {
            if (Pins.MAX.equals(firstPitching)) {
                return STRIKE;
            }

            if (secondPitching == null) {
                return BEFORE_SECOND_PITCHING;
            }

            if (Pins.MAX.equals(firstPitching.sum(secondPitching))) {
                return SPARE;
            }
            return MISS;
        }
    }
}