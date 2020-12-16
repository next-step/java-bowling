package bowling.domain.bowlinggame;

import bowling.domain.KnockDownPins;

public interface BowlingGame {
    void setKnockDownPins(KnockDownPins knockDownPins);

    boolean isEnd();

    int getCurrentFrameIndex();
}
