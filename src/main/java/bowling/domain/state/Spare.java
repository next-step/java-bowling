package bowling.domain.state;

import bowling.domain.Score;

public class Spare extends Finished {
    @Override
    public Score getScore() {
        return null;
    }

    @Override
    public String expression() {
        return null;
    }

    @Override
    public Score calculateScore(Score beforeScore) {
        return null;
    }
}
