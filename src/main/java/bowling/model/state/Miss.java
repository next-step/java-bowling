package bowling.model.state;

import bowling.model.Pins;

import java.util.Objects;

public class Miss extends Finished {

    private static final String GUTTER_DESC = "-";
    private final Pins firstPins;
    private final Pins secondPins;

    public Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public String getDesc() {
        if(isGutter()) {
            return GUTTER_DESC;
        }
        return this.secondPins.count();
    }

    private boolean isGutter() {
        return secondPins.isGutter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return Objects.equals(firstPins, miss.firstPins) && Objects.equals(secondPins, miss.secondPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPins, secondPins);
    }
}
