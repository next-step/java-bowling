package bowling.domain.state;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;

import java.util.Arrays;
import java.util.Objects;

public class FrameStateOnce implements FrameState {
    private final Pinfall firstPinfall;

    public FrameStateOnce(Pinfall pinfall) {
        firstPinfall = pinfall;
    }

    @Override
    public FrameState roll(Pinfall pinfall) {
        if (isSpare(pinfall)) {
            return new FrameStateSpare(pinfall);
        }
        return new FrameStateOpen(Arrays.asList(firstPinfall, pinfall));
    }

    @Override
    public boolean isRollable() {
        return true;
    }

    @Override
    public PointSymbols pointSymbols() {
        return new PointSymbols(PointSymbol.valueOf(firstPinfall));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameStateOnce that = (FrameStateOnce) o;
        return Objects.equals(firstPinfall, that.firstPinfall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPinfall);
    }

    private boolean isSpare(Pinfall pinfall) {
        return firstPinfall.add(pinfall).equals(new Pinfall(10));
    }
}
