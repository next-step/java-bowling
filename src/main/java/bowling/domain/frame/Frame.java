package bowling.domain.frame;

import bowling.domain.pin.FallenPins;

public interface Frame {

    String RESULT_DELIMITER = "|";

    Frame updateFrameState(FallenPins fallenPins);

    String getResult();

    boolean isFinish();

}
