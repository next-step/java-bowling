package bowling.domain.state;

import static bowling.domain.FallingPinCount.ALL_PIN_DOWN;

import bowling.domain.FallingPinCount;
import java.util.Objects;

public class Playing implements State {

    private static final String PLAYING_MESSAGE = "%s  ";

    protected final FallingPinCount first;

    protected Playing(FallingPinCount fallingPinCount) {
        this.first = fallingPinCount;
    }

    @Override
    public State roll(FallingPinCount fallingPinCount) {
        if (ALL_PIN_DOWN.equals(FallingPinCount.sum(first, fallingPinCount))) {
            return new Spare(first, fallingPinCount);
        }
        return new Open(first, fallingPinCount);
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public String reportState() {
        return String.format(PLAYING_MESSAGE, convertReportPattern(first));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Playing playing = (Playing) o;
        return Objects.equals(first, playing.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first);
    }
}
