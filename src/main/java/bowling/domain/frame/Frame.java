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

    public void pitch(final Pitch result) {
        final Optional<Pitch> first = Optional.ofNullable(this.first);

        if (!first.isPresent()) {
            this.first = result;
            return;
        }

        first.ifPresent(e -> addSecond(result));
    }

    protected void addSecond(final Pitch result) {
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
