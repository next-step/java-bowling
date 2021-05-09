package bowling.domain.state;

import bowling.domain.frame.Frame;
import bowling.domain.score.Score;

public class Ready extends Running {

    public static State initialize() {
        return new Ready();
    }

    private Ready() {
    }

    @Override
    public Frame bowl(Pins pins) {
        return null;
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
