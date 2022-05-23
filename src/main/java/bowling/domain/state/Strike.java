package bowling.domain.state;

import bowling.domain.Score;

public class Strike extends Finished {
    private final Score score;

    public Strike() {
        this.score = Score.ofStrike();
    }

    @Override
    public boolean isFinish() {
        return true;
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

}
