package bowling.domain.frame;

import bowling.domain.frame.exception.PitchResultAddException;
import java.util.Optional;

public abstract class Frame {

    protected static final int MAX = 10;

    protected PitchResult first;
    protected PitchResult second;

    protected Frame() {

    }

    protected Frame(final PitchResult first) {
        this.first = first;
    }

    protected Frame(final PitchResult first, final PitchResult second) {
        this.first = first;
        this.second = second;
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

        first.ifPresent(e -> addSecond(result));
    }

    private void addSecond(final PitchResult result) {
        if (first.add(result) > MAX) {
            throw new PitchResultAddException();
        }

        second = result;
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
