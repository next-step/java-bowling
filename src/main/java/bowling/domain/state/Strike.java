package bowling.domain.state;

import bowling.domain.score.Score;

public class Strike extends Finish {

    private static final int FULL = 10;

    public static State initialize() {
        return new Strike();
    }

    private Strike() { }

    @Override
    public Score score() {
        return Score.strike();
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
