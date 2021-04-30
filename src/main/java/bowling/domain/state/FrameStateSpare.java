package bowling.domain.state;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;

import java.util.Arrays;

public class FrameStateSpare implements FrameState {
    private final Pinfall firstPinfall;

    public FrameStateSpare() {
        firstPinfall = new Pinfall(0);
    }

    public FrameStateSpare(Pinfall pinfall) {
        this.firstPinfall = pinfall;
    }

    @Override
    public FrameState roll(Pinfall pinfall) {
        throw new IllegalArgumentException("공을 굴릴 수 없습니다");
    }

    @Override
    public boolean isRollable() {
        return false;
    }

    @Override
    public PointSymbols pointSymbols() {
        return new PointSymbols(Arrays.asList(PointSymbol.valueOf(firstPinfall), PointSymbol.SPARE));
    }
}
