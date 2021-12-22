package bowling.model.state;

import bowling.model.Pins;

import java.util.Objects;

public class Spare extends Finished {

    private static final String SPARE_DESC = "/";
    private final Pins firstPins;
    private final Pins secondPins;

    public Spare(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public String getDesc() {
        return SPARE_DESC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Spare spare = (Spare) o;
        return Objects.equals(firstPins, spare.firstPins) && Objects.equals(secondPins, spare.secondPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPins, secondPins);
    }
}
