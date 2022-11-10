package bowling.domain.frame.state;

import bowling.domain.BowlRecord;

public interface State {
    State bowl(int pins);

    BowlRecord createBowlRecord();

    boolean isFinish();

    boolean canBonusBowl();
}
