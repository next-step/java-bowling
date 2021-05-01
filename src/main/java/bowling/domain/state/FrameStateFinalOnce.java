package bowling.domain.state;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;

import java.util.Arrays;

public class FrameStateFinalOnce implements FrameState {
    private final Pinfall firstPinfall;

    public FrameStateFinalOnce(Pinfall pinfall) {
        firstPinfall = pinfall;
    }

    @Override
    public FrameState roll(Pinfall secondPinfall) {
        if (isSpare(secondPinfall)) {
            return new FrameStateBonus(Arrays.asList(firstPinfall, secondPinfall));
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

    private boolean isSpare(Pinfall secondPinfall) {
        return firstPinfall.add(secondPinfall).equals(new Pinfall(10));
    }
}
