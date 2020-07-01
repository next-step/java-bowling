package bowling.domain;

import bowling.domain.pitching.Pitching;

public interface Frame {

    Frame figureOutFrame(FallenPinNumber fallenPinNumber);

    boolean isFinalFrame();

    Pitching getPitching();
}
