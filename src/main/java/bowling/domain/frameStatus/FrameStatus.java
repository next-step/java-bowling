package bowling.domain.frameStatus;

import bowling.domain.bonusScore.BonusScore;
import bowling.domain.FrameResults;

public interface FrameStatus {
    boolean isCompleted();

    FrameStatus bowl(int numberOfHitPin);

    FrameResults calculateCurrentResult();

    BonusScore calculateBonusScore();
}
