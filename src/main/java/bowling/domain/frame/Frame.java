package bowling.domain.frame;

import bowling.domain.KnockDownPins;
import bowling.domain.pitchings.Pitchings;

public interface Frame {
    Frame initNextFrame();

    Frame setKnockDownPins(KnockDownPins knockDownPins);

    Pitchings getPitchings();

    boolean isEnd();

    int getIndex();
}
