package bowling.domain.frame.state;

import java.util.List;

import bowling.domain.BowlRecord;
import bowling.domain.frame.Score;

public class Ready extends Running {
    @Override
    public State bowl(int falledPins) {
        Pins pins = new Pins(falledPins);
        if (pins.isStrike()) {
            return new Strike();
        }

        return new FirstBowl(pins);
    }

    @Override
    public BowlRecord createBowlRecord() {
        return new BowlRecord(List.of());
    }

    @Override
    public Score calculateBonusScore(Score previousScore) {
        return previousScore;
    }

    @Override
    public boolean canBonusBowl() {
        return false;
    }
}
