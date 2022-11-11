package bowling.frame;

import bowling.FallenPins;

public interface Frame {

    String RESULT_DELIMITER = "|";

    Frame update(FallenPins fallenPins);

    String getResult();

    boolean isFinish();

}
