package bowling.domain.state;

import bowling.domain.pin.FallenPin;

import java.util.Objects;

public class Spare extends Finished {
    private final FallenPin firstPin;
    private final FallenPin secondPin;

    public Spare(FallenPin firstPin, FallenPin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public String description() {
        String result = firstPin.getCount() + Symbol.BAR + Symbol.SPARE;
        return result.replace("0", Symbol.GUTTER);
    }

    @Override
    public int tries() {
        return 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spare)) return false;

        Spare spare = (Spare) o;

        if (!Objects.equals(firstPin, spare.firstPin)) return false;
        return Objects.equals(secondPin, spare.secondPin);
    }

    @Override
    public int hashCode() {
        int result = firstPin != null ? firstPin.hashCode() : 0;
        result = 31 * result + (secondPin != null ? secondPin.hashCode() : 0);
        return result;
    }
}
