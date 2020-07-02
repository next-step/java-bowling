package bowling.domain.frame;

import bowling.domain.FallenPinNumber;
import bowling.domain.pitching.Pitching;

public interface Frame {

    Frame figureOutFrame(FallenPinNumber fallenPinNumber);

    boolean isFinalFrame();

    Pitching getPitching();

    Frame getNextFrame();
}
