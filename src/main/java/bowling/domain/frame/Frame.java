package bowling.domain.frame;

import bowling.domain.pin.FallenPins;

public interface Frame {

    Frame updateFrameState(FallenPins fallenPins);

    FallenPins getFirstTurnResult();

    FallenPins getSecondTurnResult();

    boolean isFinish();

}
