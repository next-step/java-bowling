package bowling.domain.state;

import bowling.domain.Pins;
import java.util.Objects;

public class Strike implements State{

    private final Pins falledPins;

    private Strike(Pins falledPins) {
        this.falledPins = falledPins;
    }

    public static Strike of(Pins falledPins) {
        return new Strike(falledPins);
    }

    @Override
    public State nextPitch(Pins pins) {
        throw new IllegalArgumentException("더 이상 투구할 수 없습니다.");
    }

    @Override
    public String display() {
        return "X";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Strike strike = (Strike) o;
        return Objects.equals(falledPins, strike.falledPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(falledPins);
    }
}
