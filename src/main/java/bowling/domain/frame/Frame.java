package bowling.domain.frame;

import bowling.domain.frame.exception.PitchResultAddException;
import java.util.Optional;

public class Frame {

    private static final int MAX = 10;

    private PitchResult first;
    private PitchResult second;

    private Frame() {

    }

    private Frame(final PitchResult first) {
        this.first = first;
    }

    public static Frame of() {
        return new Frame();
    }

    public static Frame of(final PitchResult first) {
        return new Frame(first);
    }

    public boolean isEnd() {
        if (Optional.ofNullable(first).orElseGet(PitchResult::zero).isStrike()) {
            return true;
        }

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

    public PitchResult getFirst() {
        return first;
    }

    public PitchResult getSecond() {
        return second;
    }
}
