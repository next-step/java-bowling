package bowling.domain.frame.state;

import java.util.List;

import bowling.domain.BowlRecord;
import bowling.domain.frame.Score;

public class Strike extends Finished {
    @Override
    public BowlRecord createBowlRecord() {
        return new BowlRecord(List.of(new Pins(10)));
    }

    @Override
    public Score getScore() {
        return new Score(Pins.MAX_PINS, 2);
    }

    @Override
    public boolean canBonusBowl() {
        return true;
    }
}
