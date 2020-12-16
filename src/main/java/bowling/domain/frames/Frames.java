package bowling.domain.frames;

import bowling.domain.KnockDownPins;

public interface Frames {
    boolean isEnd();

    void setKnockDownPins(KnockDownPins knockDownPins);

    int getCurrentFrameIndex();
}
