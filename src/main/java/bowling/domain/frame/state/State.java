package bowling.domain.frame.state;

import bowling.domain.BowlRecord;
import bowling.domain.frame.Score;

public interface State {
    State bowl(int pins);

    BowlRecord createBowlRecord();

    Score getScore();

    Score calculateBonusScore(Score previousScore);

    boolean isFinish();

    boolean canBonusBowl();
}
