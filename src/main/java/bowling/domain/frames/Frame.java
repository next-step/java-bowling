package bowling.domain.frames;

import bowling.domain.KnockDownPins;

public interface Frame {
    void setKnockDownPins(KnockDownPins knockDownPins);

    boolean isEnd();
}
