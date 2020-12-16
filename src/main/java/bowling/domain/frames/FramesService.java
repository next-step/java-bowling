package bowling.domain.frames;

import bowling.domain.KnockDownPins;

public interface FramesService {
    boolean isEnd();

    void setKnockDownPins(KnockDownPins knockDownPins);

    int getCurrentFrameIndex();
}
