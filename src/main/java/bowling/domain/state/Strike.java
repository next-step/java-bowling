package bowling.domain.state;

import bowling.domain.score.Score;

public class Strike extends Finish {

    private static final int FULL = 10;

    private Strike() { }

    public static State initialize() {
        return new Strike();
    }

    @Override
    public Score score() {
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
