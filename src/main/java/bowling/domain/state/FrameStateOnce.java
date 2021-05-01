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
    public FrameState roll(Pinfall secondPinfall) {
        if (isSpare(secondPinfall)) {
            return new FrameStateSpare(firstPinfall);
        }
        return new FrameStateOpen(Arrays.asList(firstPinfall, secondPinfall));
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

    private boolean isSpare(Pinfall secondPinfall) {
        return firstPinfall.add(secondPinfall).equals(new Pinfall(10));
    }
}
