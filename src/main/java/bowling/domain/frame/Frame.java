package bowling.domain.frame;

import bowling.domain.pitch.Pitch;
import bowling.domain.pitch.exception.PitchResultAddException;
import java.util.Optional;

public abstract class Frame {

    protected static final int MAX = 10;

    protected Pitch first;
    protected Pitch second;

    protected Frame() {

    }

    protected Frame(final Pitch first) {
        this.first = first;
    }

    protected Frame(final Pitch first, final Pitch second) {
        this.first = first;
        this.second = second;
    }

    public boolean isEnd() {
        final Pitch first = Optional.ofNullable(this.first)
            .orElseGet(Pitch::zero);
        if (first.isStrike()) {
            return true;
        }

        return this.first != null && this.second != null;
    }

    protected void addSecond(final Pitch result) {
        if (first.add(result) > MAX) {
            throw new PitchResultAddException();
        }

        second = result;
    }

    public abstract void pitch(final Pitch result);

    public abstract boolean isStrike();

    public abstract boolean isSpare();

    public int getFirst() {
        return first.getNumber();
    }

    public int getSecond() {
        return second.getNumber();
    }
}
