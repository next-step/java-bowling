package bowling.domain;

import bowling.domain.exception.InvalidPinCountException;

public class NormalFrame extends Frame {

    private static final int MAX = 10;

    public NormalFrame() {

    }

    public NormalFrame(final Pitch first) {
        super(first);
    }

    public NormalFrame(final Pitch first, final Pitch second) {
        super(first, second);
    }

    @Override
    public boolean isEnd() {
        if (first == null) {
            return false;
        }

        if (first.getPitchResult().equals(PitchResult.STRIKE)) {
            return true;
        }

        return second != null;
    }

    @Override
    public void pitch(final int pinCount) {
        if (first == null) {
            first = new Pitch(pinCount);
            return;
        }

        if (first.getPinCount() + pinCount > MAX) {
            throw new InvalidPinCountException();
        }

        second = first.next(pinCount);
    }
}
