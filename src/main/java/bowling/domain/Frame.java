package bowling.domain;

import java.util.Optional;

import static bowling.domain.NormalFrame.NORMAL_FRAME_MAXIMUM_INDEX;

public abstract class Frame {

    protected Pins firstPitching;
    protected Pins secondPitching;
    private final int index;
    private Frame nextFrame;

    protected Frame(final int index) {
        this.index = index;
    }

    public static Frame createFirst() {
        return NormalFrame.of(0);
    }

    public Frame createNext() {
        if (isBeforeFinal()) {
            nextFrame = FinalFrame.of(index + 1);
            return nextFrame;
        }

        nextFrame = NormalFrame.of(index + 1);
        return nextFrame;
    }

    private boolean isBeforeFinal() {
        return index == NORMAL_FRAME_MAXIMUM_INDEX;
    }

    public abstract void pitch(Pins pins);

    public boolean isPitchdisable() {
        return !isPitchable();
    }

    public abstract boolean isPitchable();

    public Optional<Frame> next() {
        return Optional.ofNullable(nextFrame);
    }

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