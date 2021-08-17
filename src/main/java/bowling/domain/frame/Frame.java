package bowling.domain.frame;

import bowling.domain.frame.exception.PitchResultAddException;

public class Frame {

    private static final int MAX = 10;

    private PitchResult first;
    private PitchResult second;

    private Frame() {

    }

    public static Frame of() {
        return new Frame();
    }

    public boolean isEnd() {
        return first != null && second != null;
    }

    public void pitch(final PitchResult result) {
        if (first == null) {
            first = result;
            return;
        }

        if (first.add(result) > MAX) {
            throw new PitchResultAddException();
        }

        second = result;
    }
}
