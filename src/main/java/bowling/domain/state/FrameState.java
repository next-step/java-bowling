package bowling.domain.state;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbols;
import bowling.domain.Score;

import java.util.List;

public interface FrameState {
    FrameState roll(Pinfall pinfall);

    boolean isRollable();

    PointSymbols pointSymbols();

    List<Pinfall> pinfalls();

    Score score();

    Score score(List<Pinfall> bonusPinfalls);
}
