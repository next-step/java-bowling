package bowling.domain.frame.state;

import java.util.List;

import bowling.domain.BowlRecord;

public class Strike extends Finished {
    @Override
    public BowlRecord createBowlRecord() {
        return new BowlRecord(List.of(new Pins(10)));
    }

    @Override
    public boolean canBonusBowl() {
        return true;
    }
}
