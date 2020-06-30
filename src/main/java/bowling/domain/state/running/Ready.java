package bowling.domain.state.running;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.finish.Strike;

public class Ready extends Running {

    private static final Ready INSTANCE = new Ready();

    private Ready() {
    }

    public static Ready getInstance() {
        return INSTANCE;
    }

    @Override
    public State bowl(final PinCount hitCount) {
        Pins pins = Pins.of(hitCount);

        if (pins.isStrike()) {
            return Strike.getInstance();
        }
        return FirstHit.of(pins);
    }

    @Override
    public Score calculateScoreForExtraBonusCount(final Score beforeScore) {
        return beforeScore;
    }
}
