package bowling.domain.frames;

import bowling.domain.KnockDownPins;

public interface FrameService {
    void setKnockDownPins(KnockDownPins knockDownPins);

    boolean isEnd();
}
