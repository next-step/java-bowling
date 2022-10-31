package bowling.domain.state;

import bowling.domain.score.Score;

public class Strike extends Finished {
    private static final int STRIKE_FALLEN_PINS = 10;

    @Override
    public String description() {
        return Symbol.STRIKE;
    }

    @Override
    public int tries() {
        return 1;
    }

    @Override
    public Score getScore() {
        return new Score(STRIKE_FALLEN_PINS, 2);
    }

    @Override
    public Score addScore(Score previousScore) {
        return previousScore.bowl(STRIKE_FALLEN_PINS);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Strike;
    }

    @Override
    public int hashCode() {
        return Strike.class.hashCode();
    }
}
