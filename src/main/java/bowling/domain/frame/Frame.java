package bowling.domain.frame;

import bowling.domain.KnockDownPins;
import bowling.domain.pitchings.Pitchings;

public interface Frame {
    Frame initNextFrame();

    void setKnockDownPins(KnockDownPins knockDownPins);

    Pitchings getPitchings();

    boolean isEnd();

    Frame getNextFrame();

    int getIndex();

    boolean isLastFrame();
}
