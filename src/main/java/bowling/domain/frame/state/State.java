package bowling.domain.frame.state;

import bowling.domain.BowlRecord;
import bowling.domain.frame.Score;

public interface State {
    State bowl(int pins);

    BowlRecord createBowlRecord();

    Score getScore();


    boolean isFinish();

    boolean canBonusBowl();
}
