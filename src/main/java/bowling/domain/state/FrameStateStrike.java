package bowling.domain.state;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;

public class FrameStateStrike implements FrameState{
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
        return new PointSymbols(PointSymbol.STRIKE);
    }
}
