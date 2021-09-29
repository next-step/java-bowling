package bowling.domain.score;

import java.util.List;
import java.util.Objects;

public class NormalScore implements Score {

    private final List<Pin> pins;

    private NormalScore(List<Pin> pins) {
        this.pins = pins;
    }

    static NormalScore of(List<Pin> pins) {
        return new NormalScore(pins);
    }

    @Override
    public Score saveNextPin(Pin pin) {
        return null;
    }

    @Override
    public boolean isNextStorable() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NormalScore that = (NormalScore) o;
        return Objects.equals(pins, that.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }

}
