package bowling.domain.frame;

import bowling.domain.pitch.Pitch;
import bowling.domain.pitch.exception.PitchResultAddException;

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

    protected void addSecond(final Pitch result) {
        if (first.add(result) > MAX) {
            throw new PitchResultAddException();
        }

        second = result;
    }

    public abstract boolean isEnd();

    public abstract void pitch(final Pitch result);

    public Pitch getFirst() {
        return first;
    }

    public Pitch getSecond() {
        return second;
    }
}
