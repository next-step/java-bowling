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

    public Frame(final PitchResult first, final PitchResult second) {
        this.first = first;
        this.second = second;
    }

    public static Frame of() {
        return new Frame();
    }

    public static Frame of(final PitchResult first) {
        return new Frame(first);
    }

    public static Frame of(final PitchResult first, final PitchResult second) {
        return new Frame(first, second);
    }

    public boolean isEnd() {
        final PitchResult first = Optional.ofNullable(this.first)
            .orElseGet(PitchResult::zero);
        if (first.isStrike()) {
            return true;
        }

        return this.first != null && this.second != null;
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

    public boolean isStrike() {
        return first.isStrike();
    }

    public boolean isSpare() {
        return first.getNumber() + second.getNumber() == MAX;
    }

    public int getFirst() {
        return first.getNumber();
    }

    public int getSecond() {
        return second.getNumber();
    }
}
