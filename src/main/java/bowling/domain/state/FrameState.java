package bowling.domain.state;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbols;

public interface FrameState {
    FrameState roll(Pinfall pinfall);

    boolean isRollable();

    PointSymbols pointSymbols();
}
