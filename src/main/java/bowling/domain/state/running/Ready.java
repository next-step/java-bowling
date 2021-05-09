package bowling.domain.state.running;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.domain.state.finish.Strike;

public class Ready extends Running {

    private Pins readyPins = Pins.full();

    public static State initialize() {
        return new Ready();
    }

    private Ready() {
    }

    @Override
    public State bowl(final Pins firstPins) {
        if (crush(firstPins).isEmpty()) {
            return Strike.initialize();
        }
        return FirstBowl.from(firstPins);
    }

    private final Pins crush(final Pins firstPins) {
        return readyPins.hit(firstPins.count());
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        return null;
    }

    @Override
    public String description() {
        return null;
    }
}
