package bowling.domain.state;

import bowling.domain.Score;

public class Strike extends Finished {
    private static final String STRIKE = "X";

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }

    @Override
    public String expression() {
        return STRIKE;
    }

    @Override
    public Score calculateScore(Score beforeScore) {
        if(beforeScore.isCalculable()) {
            return beforeScore;
        }
        return beforeScore.bowl(beforeScore.getScore());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
