package bowling.domain.state;

import bowling.domain.Score;

public class Strike extends Finished {
    private final Score score;

    public Strike() {
        this.score = Score.ofStrike();
    }

    @Override
    public State bowl(int countOfPins) {
        return null;
    }

    @Override
    public Score getScore() {
        return this.score;
    }

    @Override
    public String expression() {
        return "X";
    }

    @Override
    public Score calculateAddScore(Score beforeScore) {
        return null;
    }

}
