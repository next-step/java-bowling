package bowling.domain.state;

import bowling.domain.Score;

public class Strike extends Finished {
    private final Score score;

    public Strike() {
        this.score = Score.ofStrike();
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
    public Score calculateScore(Score beforeScore) {
        if(beforeScore.isCalculatable()) {
            return this.score;
        }
        return score.bowl(beforeScore.getScore());
    }

}
