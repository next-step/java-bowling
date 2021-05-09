package bowling.domain.state;

import bowling.domain.frame.Frame;
import bowling.domain.score.Score;

public final class FirstBowl extends Running {

    private final Pins firstBowl;

    private FirstBowl(final Pins firstBowl) {
        this.firstBowl = firstBowl;
    }

    public static State from(Pins firstBowl) {
        return new FirstBowl(firstBowl);
    }

    @Override
    public Frame bowl(Pins pins) {
        return null;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return null;
    }

    @Override
    public String description() {
        return null;
    }
}
