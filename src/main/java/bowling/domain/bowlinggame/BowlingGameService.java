package bowling.domain.bowlinggame;

import bowling.domain.KnockDownPins;

public interface BowlingGameService{
    void setKnockDownPins(KnockDownPins knockDownPins);

    boolean isEnd();

    int getCurrentFrameIndex();
}
