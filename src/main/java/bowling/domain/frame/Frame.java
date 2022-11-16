package bowling.domain.frame;

import bowling.domain.FallenPins;

public interface Frame {

    String RESULT_DELIMITER = "|";

    Frame updateFrameState(FallenPins fallenPins);

    String getResult();

    boolean isFinish();

}
