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
        final Optional<PitchResult> first = Optional.ofNullable(this.first);

        if (!first.isPresent()) {
            this.first = result;
            return;
        }

        first.ifPresent(e -> {
            if (e.add(result) > MAX) {
                throw new PitchResultAddException();
            }

            second = result;
        });
    }

    public PitchResult getFirst() {
        return first;
    }

    public PitchResult getSecond() {
        return second;
    }
}
